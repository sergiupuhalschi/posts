package com.sergiupuhalschi.posts.injection.modules

import com.sergiupuhalschi.posts.presentation.details.PostDetailsActivity
import com.sergiupuhalschi.posts.presentation.posts.PostsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun bindPostsActivity(): PostsActivity

    @ContributesAndroidInjector
    abstract fun bindPostDetailsActivity(): PostDetailsActivity
}