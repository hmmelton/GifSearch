package com.hmmelton.gifsearch.network

import com.hmmelton.gifsearch.Secrets
import com.hmmelton.gifsearch.models.Gif
import com.squareup.moshi.Json
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GifService {

    /**
     * This function is used to query trending GIFs.
     *
     * @param limit the number of GIFs to request from the API
     * @param offset the number of GIFs the API should skip before returning (used for pagination)
     *
     * @return response containing a list of GIFs
     */
    @GET("v1/gifs/trending?api_key=${Secrets.API_KEY}&fmt=json")
    fun getTrending(@Query("limit") limit: Int, @Query("offset") offset: Int): Call<GifResponse>

    /**
     * This function is used to query GIFs matching a specific search string.
     *
     * @param q search string against which GIFs will be compared to find relevant results
     * @param limit the number of GIFs to request from the API
     * @param offset the number of GIFs the API should skip before returning (used for pagination)
     *
     * @return response containing a list of GIFs
     */
    @GET("v1/gifs/search?api_key=${Secrets.API_KEY}&fmt=json")
    fun searchGifs(
        @Query("q") search: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Call<GifResponse>
}

data class Pagination(
    @Json(name = "total_count") val totalCount: Int,
    val count: Int,
    val offset: Int
)

/**
 * This class is necessary to parse the list of GIFs out of the network responses.
 */
data class GifResponse(
    @Json(name = "data") val gifs: List<Gif>,
    val pagination: Pagination
)