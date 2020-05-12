package com.sergiupuhalschi.posts.injection.modules

import android.app.Application
import com.sergiupuhalschi.posts.data.remote.services.AppApi
import com.google.gson.Gson
import com.sergiupuhalschi.posts.BuildConfig
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

private const val CACHE_MAX_SIZE = (10 * 1024 * 1024).toLong()
private const val TIMEOUT_SECONDS = 30L

@Module
class NetworkModule {

    @Provides
    fun provideApi(
        okHttpClientBuilder: OkHttpClient.Builder,
        retrofitBuilder: Retrofit.Builder
    ): AppApi {
        return retrofitBuilder
            .client(okHttpClientBuilder.build())
            .baseUrl(BuildConfig.API_ENDPOINT)
            .build()
            .create(AppApi::class.java)
    }

    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    }

    @Provides
    internal fun provideHttpClientBuilder(application: Application): OkHttpClient.Builder {
        val okHttpBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpBuilder.addInterceptor(interceptor)
        }

        val cacheDir = File(application.cacheDir.absolutePath, application.packageName)
        okHttpBuilder.cache(Cache(cacheDir, CACHE_MAX_SIZE))
        okHttpBuilder.connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        okHttpBuilder.writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        okHttpBuilder.readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        return okHttpBuilder
    }
}