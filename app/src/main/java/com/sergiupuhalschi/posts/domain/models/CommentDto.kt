package com.sergiupuhalschi.posts.domain.models

import com.sergiupuhalschi.posts.data.models.Comment
import com.sergiupuhalschi.posts.domain.utils.UNKNOWN_ID

data class CommentDto(
    val postId: Long,
    val id: Long,
    val name: String,
    val email: String,
    val body: String
)

fun Comment.toDto() = CommentDto(
    postId = postId ?: UNKNOWN_ID,
    id = id ?: UNKNOWN_ID,
    name = name ?: "",
    email = email ?: "",
    body = body ?: ""
)