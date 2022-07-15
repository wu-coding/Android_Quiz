package com.example.android_quiz.di

import com.example.android_quiz.api.RetrofitService
import com.example.android_quiz.repository.ApiDataRepository
import com.example.android_quiz.repository.ApiDataRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideApiDataRepository(
        apiService : RetrofitService
    ): ApiDataRepository {
        return ApiDataRepository_Impl(
            apiservice = apiService
        )
    }


}