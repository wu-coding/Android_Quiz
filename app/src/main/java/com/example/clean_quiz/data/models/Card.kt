package com.example.clean_quiz.data.models;

class Card(
    val question: String,
    var answerList: List<String>,
    val correctAnswers: List<Boolean>,
    val userSelectedAnswers: MutableList<Boolean>,
    val explanation: String?
)


class CardData(
    val answer: String,
    var inputSelected:Boolean,
    var userAnswer: Boolean,
    var correctResult: Boolean,
    var imageShown:Boolean
){
    // fun get userinput

    //
    fun getAnswerSet():Pair<Boolean,Boolean>{
        return Pair<Boolean,Boolean>(userAnswer,correctResult)
    }
    // another function to check for correctResult

}




