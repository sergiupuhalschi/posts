package com.sergiupuhalschi.posts.presentation.details

import com.sergiupuhalschi.posts.presentation.common.BaseViewModelFactory
import javax.inject.Inject
import javax.inject.Provider

class PostDetailsViewModelFactory @Inject constructor(
    override val viewModelProvider: Provider<PostDetailsViewModel>
) : BaseViewModelFactory<PostDetailsViewModel>() {

    override val viewModelClass = PostDetailsViewModel::class
}