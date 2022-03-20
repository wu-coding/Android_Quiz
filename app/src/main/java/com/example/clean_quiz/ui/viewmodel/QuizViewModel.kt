package com.example.clean_quiz.ui.viewmodel

import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clean_quiz.Answer
import com.example.clean_quiz.Question_Answers


class QuizViewModel : ViewModel() {

    private val quizData: MutableList<Question_Answers> = arrayListOf(
        Question_Answers("What is Android Jetpack?", listOf<Answer>(Answer("all of these", false), Answer("tools", true))),
            Question_Answers("NAme", listOf<Answer>(Answer("Alex", false), Answer("Jessica", true))),
            Question_Answers("Brpther", listOf<Answer>(Answer("Yes", false), Answer("No", true)))
    ) as MutableList<Question_Answers>


    val currentQuestion: MutableLiveData<Question_Answers> = MutableLiveData<Question_Answers>()

    private var rightScore:MutableLiveData<Int> = MutableLiveData<Int>(0)
    private var wrongScore:MutableLiveData<Int> = MutableLiveData<Int>(0)


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
            rightScore.value = (rightScore.value)?.plus(1)
            if (quizData.size > 0) {
                currentQuestion.setValue(quizData.removeFirst())
            } else {
         //       FragmentNav Easier to observe right score in main and change
                TODO("Fragment Navigation")
                // pass the resource id of fragment you want to navigate to callback main
            }
        } else {
            wrongScore.value = (wrongScore.value)?.plus(1)
        }
    }

    fun navigationCallback(){

    }

}