package com.hmmelton.gifsearch.views.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import com.hmmelton.gifsearch.R
import com.hmmelton.gifsearch.data.GifListAdapter
import com.hmmelton.gifsearch.models.Gif
import com.hmmelton.gifsearch.views.GridItemDecoration
import com.hmmelton.gifsearch.views.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val gifAdapter = GifListAdapter()

        // Update adapter data as more becomes available
        viewModel.data.observe(this, Observer<PagedList<Gif>> { list ->
            list?.let { newList ->
                gifAdapter.submitList(newList)
            }
        })

        // Vertical staggered layout manager will allow cells to have different heights
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS

        // Space to be used as margin of grid cells
        val spaceInPixels = resources.getDimensionPixelSize(R.dimen.grid_space)

        trendingRecyclerView.layoutManager = layoutManager
        trendingRecyclerView.addItemDecoration(GridItemDecoration(space = spaceInPixels))
        trendingRecyclerView.adapter = gifAdapter
    }
}
