package com.example.clean_quiz.data.api

import android.telecom.Call

import com.example.clean_quiz.data.models.QuizData
import com.google.gson.JsonElement
import org.json.JSONObject
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface RetrofitService {
    @GET("api/v1/questions")
    suspend fun getQuizData(@QueryMap getParam: HashMap<String, String?>):List<QuizData>
//suspend fun getQuizData(@QueryMap getParam:Map<String,String> ):retrofit2.Call<List<QuizData>>
}
//https://quizapi.io/api/v1/question

//https://stackoverflow.com/questions/56556685/how-to-get-raw-json-response-of-retrofit-in-kotlin