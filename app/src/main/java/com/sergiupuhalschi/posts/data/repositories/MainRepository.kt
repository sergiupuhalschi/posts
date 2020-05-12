package com.sergiupuhalschi.posts.data.repositories

import com.sergiupuhalschi.posts.data.models.Comment
import com.sergiupuhalschi.posts.data.models.Post
import com.sergiupuhalschi.posts.data.remote.services.AppApi
import com.sergiupuhalschi.posts.data.repositories.base.Repository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val api: AppApi
) : Repository {

    override fun getPosts(): Single<List<Post>> = api.getPosts()

    override fun getComments(postId: Long): Single<List<Comment>> = api.getComments(postId)
}