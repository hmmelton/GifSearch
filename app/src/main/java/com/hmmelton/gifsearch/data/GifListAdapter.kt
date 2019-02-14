package com.hmmelton.gifsearch.data

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.hmmelton.gifsearch.R
import com.hmmelton.gifsearch.models.Gif

class GifListAdapter : PagedListAdapter<Gif, GifListAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Gif>() {
            override fun areItemsTheSame(oldGif: Gif, newGif: Gif): Boolean {
                return oldGif.id == newGif.id
            }

            override fun areContentsTheSame(oldGif: Gif, newGif: Gif): Boolean {
                return oldGif.url == newGif.url
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.gif_list_cell, parent, false)
        return ViewHolder(itemView = itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageView = itemView.findViewById<ImageView>(R.id.gifImageView)

        fun bind(gif: Gif) {

        }
    }
}