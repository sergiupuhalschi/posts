package com.sergiupuhalschi.posts.injection.modules

import com.sergiupuhalschi.posts.data.remote.services.AppApi
import com.sergiupuhalschi.posts.data.repositories.MainRepository
import com.sergiupuhalschi.posts.data.repositories.base.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(api: AppApi): Repository = MainRepository(api)
}