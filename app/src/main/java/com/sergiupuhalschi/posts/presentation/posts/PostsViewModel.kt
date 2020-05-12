package com.sergiupuhalschi.posts.presentation.posts

import androidx.lifecycle.MutableLiveData
import com.sergiupuhalschi.posts.presentation.common.BaseViewModel
import com.sergiupuhalschi.posts.utils.SingleLiveEvent
import com.sergiupuhalschi.posts.domain.models.PostDto
import com.sergiupuhalschi.posts.domain.usecases.GetPostsUseCase
import javax.inject.Inject

class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : BaseViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val error = SingleLiveEvent<String>()
    val postViewDataList = MutableLiveData<List<PostViewData>>()
    val openDetails = SingleLiveEvent<PostDto>()

    init {
        loadPosts()
    }

    private fun loadPosts() {
        isLoading.value = true
        getPostsUseCase.perform()
            .map { it.map { it.toViewData() } }
            .doFinally { isLoading.value = false }
            .safeSubscribe({
                postViewDataList.value = it
            }, {
                error.value = it.message
            })
    }

    private fun PostDto.toViewData() = PostViewData(
        post = this,
        onClicked = { openDetails.value = it }
    )
}