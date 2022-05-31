package com.example.clean_quiz.data.api


import com.example.clean_quiz.data.models.QuestionAnswerSet

import retrofit2.http.GET

import retrofit2.http.QueryMap


interface RetrofitService {
    @GET("api/v1/questions")
    suspend fun getApiData(@QueryMap getParam: HashMap<String, String>):List<QuestionAnswerSet>
//suspend fun getQuizData(@QueryMap getParam:Map<String,String> ):retrofit2.Call<List<QuizData>>
}
//https://quizapi.io/api/v1/question

//https://stackoverflow.com/questions/56556685/how-to-get-raw-json-response-of-retrofit-in-kotlin