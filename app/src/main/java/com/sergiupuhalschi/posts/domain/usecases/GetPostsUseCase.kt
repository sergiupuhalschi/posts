package com.sergiupuhalschi.posts.domain.usecases

import com.sergiupuhalschi.posts.data.repositories.base.Repository
import com.sergiupuhalschi.posts.domain.models.PostDto
import com.sergiupuhalschi.posts.domain.models.toDto
import com.sergiupuhalschi.posts.utils.providers.schedulers.SchedulerProvider
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repository: Repository,
    private val schedulerProvider: SchedulerProvider
) : SingleUseCase<Unit, List<PostDto>> {

    override fun perform(params: Unit): Single<List<PostDto>> {
        return repository.getPosts()
            .map { posts -> posts.map { it.toDto() } }
            .applySchedulers(schedulerProvider)
    }
}