package com.sergiupuhalschi.posts.presentation.details

import androidx.lifecycle.MutableLiveData
import com.sergiupuhalschi.posts.R
import com.sergiupuhalschi.posts.domain.models.CommentDto
import com.sergiupuhalschi.posts.domain.models.PostDto
import com.sergiupuhalschi.posts.domain.usecases.GetCommentsUseCase
import com.sergiupuhalschi.posts.presentation.common.BaseViewModel
import com.sergiupuhalschi.posts.utils.SingleLiveEvent
import com.sergiupuhalschi.posts.utils.providers.StringProvider
import timber.log.Timber
import javax.inject.Inject

class PostDetailsViewModel @Inject constructor(
    private val getCommentsUseCase: GetCommentsUseCase,
    private val stringProvider: StringProvider
) : BaseViewModel() {

    val title = MutableLiveData<String>()
    val body = MutableLiveData<String>()
    val comments = MutableLiveData<List<CommentDto>>()
    val isLoading = MutableLiveData<Boolean>()
    val loadingInfo = SingleLiveEvent<String>()

    private lateinit var post: PostDto

    fun setPost(post: PostDto) {
        if (this::post.isInitialized) {
            return
        }
        this.post = post
        initData()
    }

    fun loadComments() {
        isLoading.value = true
        getCommentsUseCase.perform(post.id)
            .doAfterTerminate { isLoading.value = false }
            .safeSubscribe({
                if (it.isEmpty()) {
                    loadingInfo.value = stringProvider.getString(R.string.comments_empty)
                } else {
                    comments.value = it
                }
            }, {
                Timber.e(it)
                loadingInfo.value = stringProvider.getString(R.string.comments_error)
            })
    }

    private fun initData() {
        title.value = post.title
        body.value = post.body
        loadComments()
    }
}