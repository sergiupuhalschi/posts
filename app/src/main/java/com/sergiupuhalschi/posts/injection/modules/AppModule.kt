package com.sergiupuhalschi.posts.injection.modules

import android.app.Application
import android.content.Context
import com.sergiupuhalschi.posts.utils.providers.StringMainProvider
import com.sergiupuhalschi.posts.utils.providers.StringProvider
import com.sergiupuhalschi.posts.utils.providers.schedulers.RuntimeSchedulerProvider
import com.sergiupuhalschi.posts.utils.providers.schedulers.SchedulerProvider
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider = RuntimeSchedulerProvider()

    @Provides
    @Singleton
    fun provideStringProvider(context: Context): StringProvider = StringMainProvider(context)
}