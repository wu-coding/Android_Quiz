package com.example.clean_quiz.data

import com.example.clean_quiz.data.api.RetrofitService
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// to ensure lifecycle

object RetrofitObject {
    val moshi = Moshi.Builder()
        .add(RepoListJsonAdapter())
        .build()

    val api:RetrofitService by lazy {
        Retrofit.Builder()
            .baseUrl("https://quizapi.io")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
    }
}