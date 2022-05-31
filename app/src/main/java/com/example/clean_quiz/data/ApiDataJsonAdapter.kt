package com.example.clean_quiz.data

import com.example.clean_quiz.ApiDataJson
import com.example.clean_quiz.data.models.QuestionAnswerSet
import com.squareup.moshi.FromJson

class ApiDataJsonAdapter {

    // Might not need this class?
    @FromJson
    fun answerFromJSON(apiDataJson: ApiDataJson): QuestionAnswerSet {

        val insertQuestion = apiDataJson.question
        val correctQuestionNum = mutableSetOf<Int>()
        val insertAnswerList = arrayListOf<String>()

        apiDataJson.answers.map {
            if (it.value != null){
                insertAnswerList.add(it.value!!)
            }
        }

        var position = 0
        apiDataJson.correct_answers.map{
            if (it.value != null && it.value.toBoolean()){
                    correctQuestionNum.add(position)
            }
            position++
        }

        val insertSize = insertAnswerList.size


    return QuestionAnswerSet(
        insertQuestion,
        insertAnswerList.toList(),
        correctQuestionNum,
        insertSize
    )
    }
}
