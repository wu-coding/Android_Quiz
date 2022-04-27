package com.example.clean_quiz.data.DAO

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "record",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("userId"),
        childColumns = arrayOf("userID"),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)

data class Record(
    @PrimaryKey(autoGenerate = true) val recordId:Int,
    @ColumnInfo(name = "user_id") val userID:Int,
    @ColumnInfo(name = "category") val category:String,
    @ColumnInfo(name = "difficulty") val difficulty:String,
    @ColumnInfo(name = "question_amount") val questionAmount:Int,
    @ColumnInfo(name = "correct_answers") val correctAnswers:String,
    @ColumnInfo(name = "wrong_answers")val wrongAnswers:String,
    @ColumnInfo(name = "time_taken") val timeTaken:Int  //Can switch to Time
)

