package com.example.clean_quiz

class QuizData(val question:String, val answerList: List<Answer>)
{
    public fun getSize(): Int {
        return answerList.size
    }
}

class Answer (val answer:String, val check:Boolean){}