package com.hmmelton.gifsearch.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object GifServiceFactory {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val service = Retrofit.Builder()
        .baseUrl("https://api.giphy.com/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(GifService::class.java)

    fun create() = service
}