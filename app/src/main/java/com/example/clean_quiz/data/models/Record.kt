package com.example.clean_quiz.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.clean_quiz.data.User

@Entity(
    tableName = "record",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("user_id"),
        childColumns = arrayOf("user_id"),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)

data class Record(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "record_id")
    val recordId: Int,
    @ColumnInfo(name = "user_id") val userID: Int,
    @ColumnInfo(name = "category") val category: String?,
    @ColumnInfo(name = "difficulty") val difficulty: String?,
    @ColumnInfo(name = "question_amount") val questionAmount: Int?,
    @ColumnInfo(name = "correct_answers") val correctAnswers: Int?,
    @ColumnInfo(name = "wrong_answers") val wrongAnswers: Int?,
    @ColumnInfo(name = "total_score") val totalScore: Int?,
    @ColumnInfo(name = "time_taken") val timeTaken: String?
)
// extension functions
data class FullRecord(
    val user_id: Int,
    val record_id: Int,
    val first_name:String?,
    val last_name:String?,
    val category: String?,
    val difficulty: String?,
    val question_amount: Int?,
    val totalScore: Int?,
    val time_taken: String?
)

data class RecordPreferences(
    val record_id: Int,
    var user_id:Int,
    var category: String,
    var difficulty: String,
    var question_amount: Int
)

data class RecordScore(
    val record_id: Int,
    var correct_answers: Int,
    var wrong_answers: Int,
    var time_taken: String
)

data class SearchPreferences(
    var first_name:String?,
    var last_name:String?,
    var category: String?,
    var difficulty: String?,
)


