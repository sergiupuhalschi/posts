package com.sergiupuhalschi.posts.data.models

import com.google.gson.annotations.SerializedName

data class Comment(
    @SerializedName("psotId")
    val postId: Long? = null,
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("body")
    val body: String? = null
)