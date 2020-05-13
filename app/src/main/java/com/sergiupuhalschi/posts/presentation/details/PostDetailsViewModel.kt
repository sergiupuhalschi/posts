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

    val items = MutableLiveData<List<PostDetailsItem<*>>>()
    val isLoading = MutableLiveData<Boolean>()
    val loadingInfo = SingleLiveEvent<String>()

    private lateinit var post: PostDto

    fun setPost(post: PostDto) {
        if (this::post.isInitialized) {
            return
        }
        this.post = post
        loadComments()
    }

    fun loadComments() {
        isLoading.value = true
        getCommentsUseCase.perform(post.id)
            .doAfterTerminate { isLoading.value = false }
            .safeSubscribe({
                items.value = toItems(post, it)
            }, {
                Timber.e(it)
                loadingInfo.value = stringProvider.getString(R.string.comments_error)
            })
    }

    private fun toItems(post: PostDto, comments: List<CommentDto>) =
        ArrayList<PostDetailsItem<*>>().apply {
            add(PostDetailsItem(PostDetailsItemType.HEADER, post))
            if (isEmpty()) {
                add(
                    PostDetailsItem(
                        PostDetailsItemType.INFO,
                        stringProvider.getString(R.string.comments_empty)
                    )
                )
            } else {
                addAll(comments.map { PostDetailsItem(PostDetailsItemType.COMMENT, it) })
            }
        }
}