package com.example.clean_quiz.data.repository

import com.example.clean_quiz.QuizData
//import com.example.clean_quiz.QuizDataItem
import com.example.clean_quiz.data.api.RetrofitService
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.TypeAdapter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.addAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object QuizDataRepository {

/*    val listType = Types.newParameterizedType(List::class.java, PushPortMessageItem::class.java)
    val adapter: JsonAdapter<List<PushPortMessageItem>> = moshi.adapter(listType)*/
 //   val adapter = moshi.adapter<List<Card>>()
   // val cards: List<Card> = adapter.fromJson(cardsJsonResponse)

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()


val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

    val client : OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(interceptor)
    }.build()


    val apiService = Retrofit.Builder()
            .baseUrl("https://quizapi.io")
         //   .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
            .create(RetrofitService::class.java)


//    suspend fun getQuizData(paramMap:Map<String,String>):JsonElement = apiService.getQuizData(paramMap)
}
