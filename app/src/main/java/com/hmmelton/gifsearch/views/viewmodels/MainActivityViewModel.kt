package com.hmmelton.gifsearch.views.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.DataSource
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.util.Log
import com.hmmelton.gifsearch.data.GifDataSourceFactory
import com.hmmelton.gifsearch.data.GifPositionalDataSource
import com.hmmelton.gifsearch.models.Gif

class MainActivityViewModel : ViewModel() {

    companion object {

        /**
         * How far from the edge of loaded content we need to be before fetching another "page" of data
         */
        private const val PREFETCH_DISTANCE = 4

        /**
         * Amount of data initially loaded
         */
        private const val INITIAL_LOAD_SIZE = 20

        /**
         * Amount of data loaded in subsequent requrests
         */
        private const val PAGE_SIZE = 10
    }

    private val sourceFactory = GifDataSourceFactory(type = GifPositionalDataSource.Type.Trending)
    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPrefetchDistance(PREFETCH_DISTANCE)
        .setInitialLoadSizeHint(INITIAL_LOAD_SIZE)
        .setPageSize(PAGE_SIZE)
        .build()

    /**
     * Collection of [Gif] objects to display
     */
    val data: LiveData<PagedList<Gif>>

    /**
     * This function refreshes trending Gif data.
     *
     * @param callback lambda called after invalidation is complete
     */
    fun refreshData(callback: () -> Unit) {
        data.value?.dataSource?.let { dataSource ->
            dataSource.addInvalidatedCallback(object : DataSource.InvalidatedCallback {
                override fun onInvalidated() {
                    Log.e("MainActivityViewModel", "invalidated")
                    callback()

                    // After the invalidation is complete, remove this callback
                    dataSource.removeInvalidatedCallback(this)
                }
            })

            dataSource.invalidate()
        }
    }

    init {
        data = LivePagedListBuilder(sourceFactory, pagedListConfig).build()
    }
}