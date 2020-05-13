package com.sergiupuhalschi.posts.presentation.posts

import androidx.lifecycle.MutableLiveData
import com.sergiupuhalschi.posts.R
import com.sergiupuhalschi.posts.presentation.common.BaseViewModel
import com.sergiupuhalschi.posts.utils.SingleLiveEvent
import com.sergiupuhalschi.posts.domain.models.PostDto
import com.sergiupuhalschi.posts.domain.usecases.GetPostsUseCase
import com.sergiupuhalschi.posts.utils.providers.StringProvider
import javax.inject.Inject

class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val stringProvider: StringProvider
) : BaseViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val error = SingleLiveEvent<String>()
    val info = MutableLiveData<String>()
    val postViewDataList = MutableLiveData<List<PostViewData>>()
    val openDetails = SingleLiveEvent<PostDto>()

    init {
        loadPosts()
    }

    private fun loadPosts() {
        isLoading.value = true
        getPostsUseCase.perform()
            .map { posts -> posts.map { it.toViewData() } }
            .doOnTerminate { isLoading.value = false }
            .safeSubscribe({
                postViewDataList.value = it
                info.value = if (it.isEmpty())
                    stringProvider.getString(R.string.posts_empty) else ""
            }, {
                error.value = it.message
            })
    }

    private fun PostDto.toViewData() = PostViewData(
        post = this,
        onClicked = { openDetails.value = it }
    )
}