package com.sergiupuhalschi.posts.presentation.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sergiupuhalschi.posts.databinding.CommentItemBinding
import com.sergiupuhalschi.posts.databinding.PostDetailsHeaderItemBinding
import com.sergiupuhalschi.posts.databinding.PostDetailsInfoItemBinding
import com.sergiupuhalschi.posts.domain.models.CommentDto
import com.sergiupuhalschi.posts.domain.models.PostDto

class PostDetailsItemsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = listOf<PostDetailsItem<*>>()

    fun setItems(items: List<PostDetailsItem<*>>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (PostDetailsItemType.values()[viewType]) {
            PostDetailsItemType.HEADER -> {
                val binding = PostDetailsHeaderItemBinding.inflate(inflater, parent, false)
                HeaderVH(binding)
            }
            PostDetailsItemType.COMMENT -> {
                val binding = CommentItemBinding.inflate(inflater, parent, false)
                CommentVH(binding)
            }
            PostDetailsItemType.INFO -> {
                val binding = PostDetailsInfoItemBinding.inflate(inflater, parent, false)
                InfoVH(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (item.type) {
            PostDetailsItemType.HEADER -> {
                (holder as HeaderVH).binding.post = item.data as PostDto
            }
            PostDetailsItemType.COMMENT -> {
                (holder as CommentVH).binding.comment = item.data as CommentDto
            }
            PostDetailsItemType.INFO -> {
                (holder as InfoVH).binding.info = item.data as String
            }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = items[position].type.ordinal

    inner class HeaderVH(val binding: PostDetailsHeaderItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class CommentVH(val binding: CommentItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class InfoVH(val binding: PostDetailsInfoItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}