package com.sergiupuhalschi.posts.presentation.posts

import com.sergiupuhalschi.posts.presentation.common.BaseViewModelFactory
import javax.inject.Inject
import javax.inject.Provider

class PostsViewModelFactory @Inject constructor(
    override val viewModelProvider: Provider<PostsViewModel>
) : BaseViewModelFactory<PostsViewModel>() {

    override val viewModelClass = PostsViewModel::class
}