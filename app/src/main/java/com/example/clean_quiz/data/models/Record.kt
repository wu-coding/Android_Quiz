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
    @PrimaryKey(autoGenerate = true) val recordId: Int,
    @ColumnInfo(name = "user_id") val userID: Int,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "difficulty") val difficulty: String,
    @ColumnInfo(name = "question_amount") val questionAmount: Int,
    @ColumnInfo(name = "correct_answers") val correctAnswers: Int,
    @ColumnInfo(name = "wrong_answers") val wrongAnswers: Int,
    @ColumnInfo(name = "time_taken") val timeTaken: Int  //Can switch to Time
)
// extension functions
data class RecordPreferences(
    val recordId: Int,
    val userID: Int,
    var category: String,
    var difficulty: String,
    var questionAmount: Int
)

data class RecordScore(
    val recordID: Int,
    var correctAnswers: Int,
    var wrongAnswers: Int,
    var timeTaken: Int
)

