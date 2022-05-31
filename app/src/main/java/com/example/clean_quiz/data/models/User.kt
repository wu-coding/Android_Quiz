package com.example.clean_quiz.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// user name
// quiz info
// score

/*
class User(
    var name:String? = null,
    var category: String? = null,
    var difficutly:String? = null,
    var questionAmount: Int? = null,
    val wrongQuesiton: Int? = null,
    val rightQuestion: Int? = null,
)

class Score(    val correctAnswers:Int? = null,
                val incorrectAnswers:Int?=null)
*/


@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "user_id")
    val userId: Int,
    @ColumnInfo(name = "first_name") var firstName:String,
    @ColumnInfo(name = "last_name") var lastName:String
)

fun User.add(){

}
