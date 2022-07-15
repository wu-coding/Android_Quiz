package com.example.android_quiz.repository

import com.example.android_quiz.models.ApiResult
import kotlinx.coroutines.flow.Flow

interface ApiDataRepository {
    suspend fun getApiData(paramMap: HashMap<String, String>): Flow<ApiResult<Any>?>
}

