package com.example.clean_quiz.data.repository

import com.example.clean_quiz.data.models.QuestionAnswerSet

interface ApiDataRepository {
    suspend fun getApiData(paramMap: HashMap<String, String>): List<QuestionAnswerSet>
}

