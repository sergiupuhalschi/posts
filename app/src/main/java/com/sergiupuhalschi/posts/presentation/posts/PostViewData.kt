package com.sergiupuhalschi.posts.presentation.posts

import com.sergiupuhalschi.posts.domain.models.PostDto

data class PostViewData(
    val post: PostDto,
    private val onClicked: (PostDto) -> Unit
) {

    fun onPostClicked() {
        onClicked(post)
    }
}