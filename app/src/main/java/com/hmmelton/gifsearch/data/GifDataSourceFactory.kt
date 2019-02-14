package com.hmmelton.gifsearch.data

import android.arch.paging.DataSource
import com.hmmelton.gifsearch.models.Gif

class GifDataSourceFactory(private val type: GifPositionalDataSource.Type) : DataSource.Factory<Int, Gif>() {
    override fun create() = GifPositionalDataSource(type = type)
}