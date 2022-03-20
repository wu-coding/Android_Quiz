package com.example.clean_quiz.data.api

import android.telecom.Call
import com.example.clean_quiz.QuizData
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface RetrofitService {
    @GET("api/v1/question")
    fun getQuizData(@QueryMap getParam:Map<String,String> ):retrofit2.Call<List<QuizData>>
}
//https://quizapi.io/api/v1/question