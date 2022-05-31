package com.example.clean_quiz.data.models;

import com.example.clean_quiz.R


class QuestionAnswerSet(
    val question:String,
    val answerList:List<String>,
    val correctSet: MutableSet<Int>,
    val answerSize: Int
)
//sealed
enum class AnswerCheckStatus(){
    CORRECT,
    WRONG,
    NOTSELECTED
}

/*class Answer(
    val answer: String,
    var userAnswer: Boolean,
    var correctAnswer: Boolean
)*/




