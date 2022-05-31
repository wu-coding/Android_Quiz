package com.example.clean_quiz.data.repository

import com.example.clean_quiz.data.api.RetrofitService
import com.example.clean_quiz.data.models.QuestionAnswerSet
import javax.inject.Inject


class ApiDataRepository_Impl @Inject constructor(val apiservice: RetrofitService):ApiDataRepository{
    override suspend fun getApiData(paramMap: HashMap<String, String>): List<QuestionAnswerSet>{
        return apiservice.getApiData(paramMap)
    }
}
