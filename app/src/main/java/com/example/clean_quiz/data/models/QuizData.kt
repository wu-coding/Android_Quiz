package com.example.clean_quiz.data.models;

class QuizData(
        val question: String,
        var answerList:List<String>,
        val correctAnswers:List<Boolean>,
        val userSelectedAnswers:MutableList<Boolean>,
        val explanation:String?
)



