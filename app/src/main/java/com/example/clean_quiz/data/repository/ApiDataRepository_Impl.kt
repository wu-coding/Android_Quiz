package com.example.clean_quiz.data.repository

import com.example.clean_quiz.data.api.RetrofitService
import com.example.clean_quiz.data.models.Card


class ApiDataRepository_Impl(val apiservice: RetrofitService):ApiDataRepository{
    override suspend fun getApiData(paramMap: HashMap<String, String?>): List<Card>{
        return apiservice.getApiData(paramMap)
    }
}
