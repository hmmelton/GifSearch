package com.hmmelton.gifsearch.data

import android.arch.paging.PagedListAdapter
import android.net.Uri
import android.support.v4.widget.CircularProgressDrawable
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hmmelton.gifsearch.R
import com.hmmelton.gifsearch.models.Gif
import com.hmmelton.gifsearch.network.GlideApp

class GifListAdapter : PagedListAdapter<Gif, GifListAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Gif>() {
            override fun areItemsTheSame(oldGif: Gif, newGif: Gif): Boolean {
                return oldGif.id == newGif.id
            }

            override fun areContentsTheSame(oldGif: Gif, newGif: Gif): Boolean {
                return oldGif.gifUrl == newGif.gifUrl
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.gif_list_cell, parent, false)
        return ViewHolder(itemView = itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { gif ->
            holder.bind(gif = gif)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageView = itemView.findViewById<ImageView>(R.id.gifImageView)

        /**
         * Progress drawable to be used as a loading indicator for GIFs
         */
        private val circularProgressDrawable = CircularProgressDrawable(itemView.context).apply {
            strokeWidth = 5f
            centerRadius = 20f
        }

        fun bind(gif: Gif) {
            circularProgressDrawable.start()

            GlideApp.with(imageView.context)
                .asGif()
                .load(Uri.parse(gif.gifUrl))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(circularProgressDrawable)
                .into(imageView)
        }
    }
}