package com.example.android_quiz.models

// Classes for Room convenience methods

data class FullRecord(
    val user_id: Int,
    val record_id: Int,
    val first_name: String?,
    val last_name: String?,
    val category: String?,
    val difficulty: String?,
    val question_amount: Int?,
    val total_score: Int?,
    val time_taken: String?
)

data class RecordPreferences(
    var record_id: Int,
    var user_id: Int,
    var category: String?,
    var difficulty: String?,
    var question_amount: Int?
)

data class RecordScore(
    val record_id: Int,
    var correct_answers: Int,
    var wrong_answers: Int,
    var total_score: Int?,
    var time_taken: String
) {
    constructor(recordId: Int, score: QuizScore) : this(
        recordId,
        score.correct,
        score.wrong,
        score.totalScore,
        score.timeTaken
    )
}

data class SearchPreferences(
    var first_name: String?,
    var last_name: String?,
    var category: String?,
    var difficulty: String?,
)
