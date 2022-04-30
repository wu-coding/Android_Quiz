package com.example.clean_quiz

import com.squareup.moshi.Json



data class ApiDataJson(
// maybe try list map? List<Answer> Answer<String,String> dont even need custom adapter
    // https://github.com/toranokopg/moshi-pair-adapter moshi add custom adapter?

    val answers: Map<String,String?>,
    val category: String,
    val correct_answer: String?,
    val correct_answers:Map<String,String?>,
    val description: Any? ,
    val difficulty: String,
    val explanation: Any?,
    val id: Int,
    val multiple_correct_answers: String,
    val question: String,
    val tags: List<Tag>?,
    val tip: Any?
)
data class Tag(
    val name: String?
)



