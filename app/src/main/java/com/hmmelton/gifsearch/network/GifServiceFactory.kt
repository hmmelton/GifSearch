package com.hmmelton.gifsearch.network

import com.hmmelton.gifsearch.Secrets
import retrofit2.Retrofit

object GifServiceFactory {
    private val service by lazy {
        Retrofit.Builder()
            .baseUrl(Secrets.API_KEY)
            .build()
            .create(GifService::class.java)
    }

    fun create() = service
}