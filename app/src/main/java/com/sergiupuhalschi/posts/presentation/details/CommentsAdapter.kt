package com.sergiupuhalschi.posts.presentation.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sergiupuhalschi.posts.databinding.CommentItemBinding
import com.sergiupuhalschi.posts.domain.models.CommentDto

class CommentsAdapter : RecyclerView.Adapter<CommentsAdapter.CommentVH>() {

    private var comments = listOf<CommentDto>()

    fun setComments(comments: List<CommentDto>) {
        this.comments = comments
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CommentItemBinding.inflate(inflater, parent, false)
        return CommentVH(binding)
    }

    override fun onBindViewHolder(holder: CommentVH, position: Int) {
        holder.binding.comment = comments[position]
    }

    override fun getItemCount(): Int = comments.size

    inner class CommentVH(val binding: CommentItemBinding) : RecyclerView.ViewHolder(binding.root)
}