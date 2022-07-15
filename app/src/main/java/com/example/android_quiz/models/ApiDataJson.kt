package com.example.android_quiz


data class ApiDataJson(
    val answers: Map<String,String?>,
    val category: String,
    val correct_answer: String?,
    val correct_answers: Map<String,String?>,
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




