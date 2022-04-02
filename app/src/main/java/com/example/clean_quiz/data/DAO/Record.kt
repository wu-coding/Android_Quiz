package com.example.clean_quiz.data.DAO

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Records")
data class Record(
    @PrimaryKey (autoGenerate = true) val id:Int,
    @ColumnInfo(name = "user_name") val userName:String,
    @ColumnInfo(name = "category") val category:String,
    @ColumnInfo(name = "question_amount") val questionAmount:Int,
    @ColumnInfo(name = "correct_answers") val correctAnswers:String,
    @ColumnInfo(name = "wrong_answers")val wrongAnswers:String,
    @ColumnInfo(name = "time_taken") val timeTaken:Int  //Can switch to Time
)
