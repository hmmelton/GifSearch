package com.hmmelton.gifsearch.models

import com.squareup.moshi.Json

data class Gif(
    val id: String,
    @Json(name = "images") private val gifImage: GifImage,
    val gifUrl: String = gifImage.fixedWidthImage.url
)