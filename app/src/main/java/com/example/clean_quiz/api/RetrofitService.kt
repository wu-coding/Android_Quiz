package com.example.clean_quiz.api


import com.example.clean_quiz.models.QuestionAnswerSet
import retrofit2.Response

import retrofit2.http.GET

import retrofit2.http.QueryMap


interface RetrofitService {
    @GET("api/v1/questions")
    suspend fun getApiData(@QueryMap getParam: HashMap<String, String>): Response<MutableList<QuestionAnswerSet>>
}
