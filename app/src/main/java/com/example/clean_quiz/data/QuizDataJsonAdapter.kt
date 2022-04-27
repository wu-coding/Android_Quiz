package com.example.clean_quiz.data

import com.example.clean_quiz.QuizDataJson
import com.example.clean_quiz.data.models.QuizData
import com.squareup.moshi.FromJson

class QuizDataJsonAdapter {

    // Might not need this class?
    @FromJson
    fun answerFromJSON(quizDataJson: QuizDataJson):QuizData{
    val answerList = ArrayList<String>()
    val correctAnswers = ArrayList<Boolean>()
    quizDataJson.answers.values.map {
        if(it != null)  {
            answerList.add(it)
        }}

    quizDataJson.correct_answers.values.mapIndexed {
            index, it -> if (index < answerList.size) {correctAnswers.add(it.toBoolean())} }

    val userChoices = MutableList<Boolean>(answerList.size, {false})

    return QuizData(
        quizDataJson.question,
        answerList,
        correctAnswers,
        userChoices,
        quizDataJson.explanation.toString()
    )
    }
}
