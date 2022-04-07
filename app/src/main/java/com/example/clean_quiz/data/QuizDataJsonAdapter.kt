package com.example.clean_quiz.data

import com.example.clean_quiz.QuizDataJson
import com.example.clean_quiz.data.models.QuizData
import com.squareup.moshi.FromJson

//import com.example.clean_quiz.Answers
//import com.squareup.moshi.FromJson

class QuizDataJsonAdapter {
// fix null
    @FromJson
    fun answerFromJSON(quizDataJson: QuizDataJson):QuizData{
    val answerList = ArrayList<QuizData.Answer>()

    val it: Iterator<*> = quizDataJson.answers.entries.iterator()
    val it2: Iterator<*> = quizDataJson.correct_answers.entries.iterator()

    // run Test to see if it is map are equal size?
    //Iterate through both maps at same time and place into Answer object
    while (it.hasNext()) {
        val pair:Map.Entry<String,String?>  = it.next() as Map.Entry<String, String?>
        val answerString = pair.value

        if(answerString == null){
            break
        }else{
            val pair2:Map.Entry<String,String?> = it2.next() as Map.Entry<String, String?>
            val checkAnswer = pair2.value.toString().toBoolean()

            answerList.add(QuizData.Answer(answerString,checkAnswer))
        }
        }

    return QuizData(
        quizDataJson.question,
        quizDataJson.multiple_correct_answers,
        answerList.toList(),
        quizDataJson.explanation.toString()
    )
    }
}
