package com.sergiupuhalschi.posts.data.repositories.base

import com.sergiupuhalschi.posts.data.models.Comment
import com.sergiupuhalschi.posts.data.models.Post
import io.reactivex.rxjava3.core.Single

interface Repository {

    fun getPosts(): Single<List<Post>>

    fun getComments(postId: Long): Single<List<Comment>>
}