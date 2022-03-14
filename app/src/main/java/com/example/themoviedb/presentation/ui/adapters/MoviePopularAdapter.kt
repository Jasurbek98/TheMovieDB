package com.example.themoviedb.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.data.remote.response.MovieItem
import com.example.themoviedb.databinding.MovieItemBinding

/**
 * Created by Jasurbek Kurganbayev 13/03/2022
 */
class MoviePopularAdapter :
    ListAdapter<MovieItem, MoviePopularAdapter.VH>(ITEM_FINISHED_IDEA_CALLBACK) {

    companion object {
        var ITEM_FINISHED_IDEA_CALLBACK = object : DiffUtil.ItemCallback<MovieItem>() {
            override fun areItemsTheSame(
                oldItem: MovieItem,
                newItem: MovieItem
            ): Boolean {
                return newItem.id == oldItem.id && newItem.hashCode() == oldItem.hashCode()
            }

            override fun areContentsTheSame(
                oldItem: MovieItem,
                newItem: MovieItem
            ): Boolean {
                return newItem.id == oldItem.id && newItem.adult == oldItem.adult
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(
        MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    )

    override fun onBindViewHolder(holder: VH, position: Int) {

    }

    inner class VH(private val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}