package com.hmmelton.gifsearch.views.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.hmmelton.gifsearch.R
import com.hmmelton.gifsearch.data.GifListAdapter
import com.hmmelton.gifsearch.data.GifPositionalDataSource
import com.hmmelton.gifsearch.models.Gif
import com.hmmelton.gifsearch.views.GridItemDecoration
import com.hmmelton.gifsearch.views.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    /**
     * Adapter used to load data into the Activity's RecyclerView
     */
    private val gifAdapter = GifListAdapter()

    /**
     * This [Observer] is used to update the RecyclerView UI when the view model's data changes.
     */
    private val dataObserver = Observer<PagedList<Gif>> { list ->
        list?.let { newList ->
            gifAdapter.submitList(newList)
        }
    }

    /**
     * This [Observer] is used to update visibility of the search bar back button
     */
    private val dataTypeObserver = Observer<GifPositionalDataSource.Type> { dataType ->
        val type = dataType ?: return@Observer

        if (type is GifPositionalDataSource.Type.Trending) {
            searchBackButton.visibility = View.GONE
        } else {
            searchBackButton.visibility = View.VISIBLE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java).apply {
            currentDataSourceType.observe(this@MainActivity, dataTypeObserver)
        }

        // Default to displaying trending GIFs
        observeTrendingGifs()

        setUpRecyclerView()
        setUpSwipeRefreshLayout()
        setUpSearchView()
        setUpBackButton()
    }

    private fun setUpRecyclerView() {

        // Vertical staggered layout manager will allow cells to have different heights
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS

        // Space to be used as margin of grid cells
        val spaceInPixels = resources.getDimensionPixelSize(R.dimen.grid_space)

        trendingRecyclerView.layoutManager = layoutManager
        trendingRecyclerView.addItemDecoration(GridItemDecoration(space = spaceInPixels))
        trendingRecyclerView.adapter = gifAdapter

        trendingRecyclerView.setOnTouchListener { _, _ ->
            searchView.clearFocus()
            return@setOnTouchListener false
        }
    }

    private fun setUpSwipeRefreshLayout() {
        trendingSwipeRefresh.setOnRefreshListener {

            // On swipe, refresh the view model's data
            viewModel.refreshData { trendingSwipeRefresh.isRefreshing = false }
        }
    }

    private fun setUpSearchView() {

        // When the user submits a search query, pull relevant GIFs from the data source
        searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                observeSearchedGifs(searchQuery = query)
                return false
            }

            override fun onQueryTextChange(newText: String) = false
        })
    }

    private fun setUpBackButton() {
        searchBackButton.setOnClickListener {
            searchView.setQuery("", false) // Clear query field
            searchView.clearFocus()
            observeTrendingGifs()
        }
    }

    private fun observeTrendingGifs() {
        toolbar.title = getString(R.string.trending_gifs)
        viewModel.setTrendingData()

        // Because switching data sets requires the view model's data object to be changed, it is necessary to add an
        // observer each time
        viewModel.data.observe(this@MainActivity, dataObserver)
    }

    private fun observeSearchedGifs(searchQuery: String) {
        toolbar.title = searchQuery
        viewModel.setSearchData(searchQuery = searchQuery)

        // Because switching data sets requires the view model's data object to be changed, it is necessary to add an
        // observer each time
        viewModel.data.observe(this@MainActivity, dataObserver)
    }
}
