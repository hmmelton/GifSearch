package com.hmmelton.gifsearch.models

import com.squareup.moshi.Json

data class GifImage(
    @Json(name = "fixed_width") val fixedWidthImage: FixedWidthImage
)

data class FixedWidthImage(val url: String, val mp4_size: String)