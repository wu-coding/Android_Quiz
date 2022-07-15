package com.example.android_quiz.models

class QuizScore(var correct: Int = 0,
            var wrong: Int = 0,
            var totalScore: Int = 0,
            var timeTaken:String = "") {

    fun updateTime(timeParam:String){
        timeTaken = timeParam
    }

    fun updateScore(score:Boolean){
        if(score){
            correct++
        }else{
            wrong++
        }
        if(correct > wrong){
            totalScore = correct - wrong
        }else{
            totalScore = 0
        }
    }
}