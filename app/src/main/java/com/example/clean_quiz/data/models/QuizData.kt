package com.example.clean_quiz.data.models;

class QuizData(
        val question: String,
        val multipleAnswers: String?,
        var answerArray:List<Answer>,
        val explanation:String?
        val singleCorrectAnswers,
        val multipleCorrectAnswers
)
{
    class Answer(var answer:String? = null, var check:Boolean? = null){}
}


