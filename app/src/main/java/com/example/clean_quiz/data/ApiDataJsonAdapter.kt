package com.example.clean_quiz.data

import com.example.clean_quiz.ApiDataJson
import com.example.clean_quiz.data.models.Card
import com.squareup.moshi.FromJson

class ApiDataJsonAdapter {

    // Might not need this class?
    @FromJson
    fun answerFromJSON(apiDataJson: ApiDataJson):Card{
    val answerList = ArrayList<String>()
    val correctAnswers = ArrayList<Boolean>()
        apiDataJson.answers.values.map {
        if(it != null)  {
            answerList.add(it)
        }}

        apiDataJson.correct_answers.values.mapIndexed {
            index, it -> if (index < answerList.size) {correctAnswers.add(it.toBoolean())} }

    val userChoices = MutableList<Boolean>(answerList.size, {false})

    return Card(
        "temp",
        answerList,
        correctAnswers,
        userChoices,
        apiDataJson.explanation.toString()
    )
    }
}
