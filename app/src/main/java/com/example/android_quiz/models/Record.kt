package com.example.android_quiz.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.android_quiz.data.User

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


