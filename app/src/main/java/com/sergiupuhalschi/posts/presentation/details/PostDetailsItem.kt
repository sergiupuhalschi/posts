package com.sergiupuhalschi.posts.presentation.details

data class PostDetailsItem<T>(
    val type: PostDetailsItemType,
    val data: T
)

enum class PostDetailsItemType {

    HEADER,
    COMMENT,
    INFO
}