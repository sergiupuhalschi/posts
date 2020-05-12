package com.sergiupuhalschi.posts.domain.usecases

import com.sergiupuhalschi.posts.data.repositories.base.Repository
import com.sergiupuhalschi.posts.domain.models.CommentDto
import com.sergiupuhalschi.posts.domain.models.toDto
import com.sergiupuhalschi.posts.utils.providers.schedulers.SchedulerProvider
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(
    private val repository: Repository,
    private val schedulerProvider: SchedulerProvider
) : SingleUseCase<Long, List<CommentDto>> {

    override fun perform(params: Long): Single<List<CommentDto>> {
        return repository.getComments(params)
            .map { comments -> comments.map { it.toDto() } }
            .applySchedulers(schedulerProvider)
    }
}