package com.hmmelton.gifsearch.models

import com.squareup.moshi.Json

data class Gif(
    val id: String,
    @Json(name = "bitly_gif_url") val url: String
)