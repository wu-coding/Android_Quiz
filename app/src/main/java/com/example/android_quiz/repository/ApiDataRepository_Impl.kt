package com.example.android_quiz.repository

import com.example.android_quiz.api.RetrofitService
import com.example.android_quiz.models.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class ApiDataRepository_Impl @Inject constructor(val apiservice: RetrofitService):ApiDataRepository{
    override suspend fun getApiData(paramMap: HashMap<String, String>): Flow<ApiResult<Any>?> {
        return flow {
            emit(ApiResult.Loading(true))   // 1. Loading State
            val response = apiservice.getApiData(paramMap)
            if (response.isSuccessful) {
                emit(ApiResult.Success(response.body()))   // 2. Success State
            } else {
                val errorMsg = response.errorBody()?.string()
                response.errorBody()?.close()
                emit(errorMsg?.let { ApiResult.Error(it) })
            }
        }
    }
}
