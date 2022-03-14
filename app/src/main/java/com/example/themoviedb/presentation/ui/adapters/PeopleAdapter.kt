package com.example.themoviedb.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.data.remote.response.CastItem
import com.example.themoviedb.data.remote.response.MovieItem
import com.example.themoviedb.databinding.ActorItemBinding
import com.example.themoviedb.databinding.MovieItemBinding
import com.example.themoviedb.utils.`typealias`.SingleBlock
import com.example.themoviedb.utils.extensions.bindItem
import com.example.themoviedb.utils.extensions.loadImageUrlWithBaseUrl
import com.example.themoviedb.utils.show

/**
 * Created by Jasurbek Kurganbayev 14/03/2022
 */
class PeopleAdapter :
    ListAdapter<CastItem, PeopleAdapter.VH>(ITEM_FINISHED_IDEA_CALLBACK) {

    private var listenerCallback: SingleBlock<CastItem>? = null


    companion object {
        var ITEM_FINISHED_IDEA_CALLBACK = object : DiffUtil.ItemCallback<CastItem>() {
            override fun areItemsTheSame(
                oldItem: CastItem,
                newItem: CastItem
            ): Boolean {
                return newItem.id == oldItem.id && newItem.hashCode() == oldItem.hashCode()
            }

            override fun areContentsTheSame(
                oldItem: CastItem,
                newItem: CastItem
            ): Boolean {
                return newItem.id == oldItem.id && newItem.adult == oldItem.adult
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(
        ActorItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    )

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind()

    fun setOnClickListener(block: SingleBlock<CastItem>) {
        listenerCallback = block
    }

    inner class VH(private val binding: ActorItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.apply {
                profileImage.setOnClickListener {
                    listenerCallback?.invoke(getItem(absoluteAdapterPosition))
                }
            }
        }

        fun bind() = bindItem {
            val data = getItem(absoluteAdapterPosition)
            binding.apply {
                name.text = data.originalName
                position.text = data.knownForDepartment
                if (data.profilePath != null) {
                    profileImage.loadImageUrlWithBaseUrl(data.profilePath!!)
                }
            }
        }
    }
}