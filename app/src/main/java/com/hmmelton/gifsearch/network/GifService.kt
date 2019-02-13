package com.hmmelton.gifsearch.network

import com.hmmelton.gifsearch.Secrets
import com.hmmelton.gifsearch.models.Gif
import com.squareup.moshi.Json
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GifService {

    @GET("v1/gifs/trending?api_key=${Secrets.API_KEY}&fmt=json")
    fun getTrending(@Query("limit") limit: Int, @Query("offset") offset: Int): Call<GifResponse>

    @GET("v1/gifs/search?api_key=${Secrets.API_KEY}&fmt=json")
    fun searchGifs(
        @Query("q") search: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Call<GifResponse>
}

data class GifResponse(
    @Json(name = "data") val gifs: List<Gif>
)