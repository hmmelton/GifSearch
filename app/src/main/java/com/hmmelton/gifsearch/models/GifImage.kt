package com.hmmelton.gifsearch.models

import com.squareup.moshi.Json

/**
 * These classes are needed to parse through the API's JSON response object
 */
data class GifImage(
    @Json(name = "fixed_width") val fixedWidthImage: FixedWidthImage
)

data class FixedWidthImage(val url: String)