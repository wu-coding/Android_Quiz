package com.example.clean_quiz.di

import android.content.Context
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.clean_quiz.data.DAO.Database
import com.example.clean_quiz.data.api.RetrofitService
import com.example.clean_quiz.data.repository.ApiDataRepository
import com.example.clean_quiz.data.repository.ApiDataRepository_Impl
import com.example.clean_quiz.data.repository.QuizDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
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

    @Singleton
    @Provides
    fun provideQuizDataRepository( database: Database): QuizDataRepository {
        return QuizDataRepository_Impl(
            database =
        )
    }
}