package com.sergiupuhalschi.posts.presentation.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sergiupuhalschi.posts.databinding.PostItemBinding

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostVH>() {

    private var posts = listOf<PostViewData>()

    fun updateList(posts: List<PostViewData>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PostItemBinding.inflate(inflater, parent, false)
        return PostVH(binding)
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: PostVH, position: Int) {
        val post = posts[position]
        holder.binding.viewData = post
    }

    inner class PostVH(
        val binding: PostItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}