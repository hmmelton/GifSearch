package com.hmmelton.gifsearch.data

import android.arch.paging.PositionalDataSource
import com.hmmelton.gifsearch.models.Gif
import com.hmmelton.gifsearch.network.GifResponse
import com.hmmelton.gifsearch.network.GifService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GifPositionalDataSource(private val type: Type, private val gifService: GifService) : PositionalDataSource<Gif>() {

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Gif>) {
        val networkCallback = getNetworkCallback { gifList -> callback.onResult(gifList) }

        when (type) {
            is Type.Trending -> {
                gifService.getTrending(
                    limit = params.loadSize,
                    offset = params.startPosition
                ).enqueue(networkCallback)
            }
            is Type.Search -> {
                gifService.searchGifs(
                    search = type.search,
                    limit = params.loadSize,
                    offset = params.startPosition
                ).enqueue(networkCallback)
            }
        }
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Gif>) {
        val networkCallback = getNetworkCallback { gifList -> callback.onResult(gifList, 0) }

        when (type) {
            is Type.Trending -> {
                gifService.getTrending(
                    limit = params.requestedLoadSize,
                    offset = params.requestedStartPosition
                ).enqueue(networkCallback)
            }
            is Type.Search -> {
                gifService.searchGifs(
                    search = type.search,
                    limit = params.requestedLoadSize,
                    offset = params.requestedStartPosition
                ).enqueue(networkCallback)
            }
        }
    }

    private fun getNetworkCallback(callback: (List<Gif>) -> Unit) = object : Callback<GifResponse> {
        override fun onFailure(call: Call<GifResponse>, t: Throwable) {
            callback(emptyList())
        }

        override fun onResponse(call: Call<GifResponse>, response: Response<GifResponse>) {
            callback(response.body()?.gifs ?: emptyList())
        }
    }

    /**
     * This sealed class is used to define the type of GIF query to be made.
     */
    sealed class Type {

        /**
         * Class used to represent trending GIFs
         */
        object Trending : Type()

        /**
         * Class used to represent a GIF search
         */
        data class Search(val search: String) : Type()
    }
}