package com.sergiupuhalschi.posts.data.models

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("userId")
    val userId: Long? = null,
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("body")
    val body: String? = null
)