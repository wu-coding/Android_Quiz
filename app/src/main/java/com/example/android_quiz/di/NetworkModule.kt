package com.example.android_quiz.di

import android.content.Context
import androidx.room.Room
import com.example.android_quiz.adapter.ApiDataJsonAdapter
import com.example.android_quiz.DAO.RecordDao
import com.example.android_quiz.DAO.UserDao
import com.example.android_quiz.api.RetrofitService
import com.example.android_quiz.models.Database
import com.example.android_quiz.repository.QuizDataRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.toranoko0518.moshi.adapter.PairAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideMoshi():Moshi = Moshi.Builder()
            .add(PairAdapterFactory())
            .add(ApiDataJsonAdapter())
            .add(KotlinJsonAdapterFactory())
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(moshi:Moshi):RetrofitService = Retrofit.Builder()
            .baseUrl("https://quizapi.io")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(RetrofitService::class.java)

    @Singleton
    @Provides
    fun provideDatabase( @ApplicationContext appContext: Context): Database {
        return Room.databaseBuilder(appContext,
            Database::class.java, "record_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideUserDao(db: Database) = db.UserDao()

    @Singleton
    @Provides
    fun provideRecordDao(db: Database) = db.RecordDao()

    @Singleton
    @Provides
    fun provideRepository(db: Database, userDao: UserDao, recordDao: RecordDao):QuizDataRepository{
        return QuizDataRepository(db, userDao, recordDao)
    }

}