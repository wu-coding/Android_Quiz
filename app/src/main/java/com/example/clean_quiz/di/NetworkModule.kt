package com.example.clean_quiz.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.clean_quiz.data.ApiDataJsonAdapter
import com.example.clean_quiz.data.DAO.RecordDao
import com.example.clean_quiz.data.DAO.UserDao
import com.example.clean_quiz.data.models.Database
import com.example.clean_quiz.data.api.RetrofitService
import com.example.clean_quiz.data.repository.QuizDataRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
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