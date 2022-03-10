package com.example.clean_quiz

import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class QuizViewModel : ViewModel() {

    private val quizData: MutableList<Question_Answers> = arrayListOf(Question_Answers("What is Android Jetpack?", listOf<Answer>(Answer("all of these", false), Answer("tools", true))),
            Question_Answers("NAme", listOf<Answer>(Answer("Alex", false), Answer("Jessica", true))),
            Question_Answers("Brpther", listOf<Answer>(Answer("Yes", false), Answer("No", true)))) as MutableList<Question_Answers>


    val currentQuestion: MutableLiveData<Question_Answers> = MutableLiveData<Question_Answers>()


    private var rightScore: LiveData<Int>
    val rightScore
    get() {

        }
        set(value) = 0

    //https://kotlinlang.org/docs/properties.html#declaring-properties

    private var wrongScore: Int = 0


    fun loadQuestion(question: TextView) {
        question.text = currentQuestion.value?.question
    }

    fun loadAnswers() {
        currentQuestion.value = quizData.removeFirst()
    }

    fun shuffleAnswers() {
        currentQuestion.value?.answerList?.shuffled()
    }

    fun checkAnswer(position: Int) {
        if (currentQuestion.value!!.answerList.get(position).check) {
            rightScore++
            if (quizData.size > 0) {
                currentQuestion.setValue(quizData.removeFirst())
            } else {
         //       FragmentNav
                TODO("Fragment Navigation")
            }
        } else {
            wrongScore++
        }
    }

}