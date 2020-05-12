package com.sergiupuhalschi.posts.data.remote.services

import com.sergiupuhalschi.posts.data.models.Comment
import com.sergiupuhalschi.posts.data.models.Post
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface AppApi {

    @GET("posts")
    fun getPosts(): Single<List<Post>>

    @GET("posts/{postId}/comments")
    fun getComments(@Path("postId") postId: Long): Single<List<Comment>>
}