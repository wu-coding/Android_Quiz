package com.example.clean_quiz.repository

import com.example.clean_quiz.models.ApiResult
import kotlinx.coroutines.flow.Flow

interface ApiDataRepository {
    suspend fun getApiData(paramMap: HashMap<String, String>): Flow<ApiResult<Any>?>
}

