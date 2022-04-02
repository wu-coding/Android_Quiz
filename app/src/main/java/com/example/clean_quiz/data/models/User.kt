package com.example.clean_quiz.data



class User(
    var name:String? = null,
    var category: String? = null,
    var difficutly:String? = null,
    var questionAmount: Int? = null,
    val wrongQuesiton: Int? = null,
    val rightQuestion: Int? = null,
)

class Score{
    val correctAnswers:Int? = null,
    val incorrectAnswers:Int?=null

}



