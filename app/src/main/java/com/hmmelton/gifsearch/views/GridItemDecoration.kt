package com.hmmelton.gifsearch.views

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * This class adds margin to cells in a [RecyclerView].
 */
class GridItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.top = space
        outRect.bottom = space
        outRect.left = space
        outRect.right = space
    }
}