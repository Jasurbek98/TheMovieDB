package com.example.themoviedb.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.data.remote.response.ActorCastItem
import com.example.themoviedb.databinding.MovieItemBinding
import com.example.themoviedb.utils.`typealias`.SingleBlock
import com.example.themoviedb.utils.extensions.bindItem
import com.example.themoviedb.utils.extensions.loadImageUrlWithBaseUrl
import com.example.themoviedb.utils.show

/**
 * Created by Jasurbek Kurganbayev 13/03/2022
 */
class MovieCreditAdapter :
    ListAdapter<ActorCastItem, MovieCreditAdapter.VH>(ITEM_FINISHED_IDEA_CALLBACK) {

    private var listenerCallback: SingleBlock<ActorCastItem>? = null


    companion object {
        var ITEM_FINISHED_IDEA_CALLBACK = object : DiffUtil.ItemCallback<ActorCastItem>() {
            override fun areItemsTheSame(
                oldItem: ActorCastItem,
                newItem: ActorCastItem
            ): Boolean {
                return newItem.id == oldItem.id && newItem.hashCode() == oldItem.hashCode()
            }

            override fun areContentsTheSame(
                oldItem: ActorCastItem,
                newItem: ActorCastItem
            ): Boolean {
                return newItem.id == oldItem.id && newItem.adult == oldItem.adult
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(
        MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    )

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind()

    fun setOnClickListener(block: SingleBlock<ActorCastItem>) {
        listenerCallback = block
    }

    inner class VH(private val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.apply {
                movieImage.setOnClickListener {
                    listenerCallback?.invoke(getItem(absoluteAdapterPosition))
                }
            }
        }

        fun bind() = bindItem {
            val data = getItem(absoluteAdapterPosition)
            binding.apply {
                if (!data.adult!!) {
                    isForAdult.show()
                }
                movieTitle.text = data.originalTitle
                if (data.posterPath != null) {
                    movieImage.loadImageUrlWithBaseUrl(data.posterPath)
                }
            }
        }
    }
}