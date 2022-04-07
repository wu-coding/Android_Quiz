package com.example.clean_quiz.ui.viewmodel

import android.app.Application
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.clean_quiz.data.models.QuizData
import com.example.clean_quiz.data.repository.QuizDataRepository
import com.example.clean_quiz.ui.views.QuizFragment
import kotlinx.coroutines.async

import kotlinx.coroutines.launch

//import com.example.clean_quiz.Answer

// maybe convert quizdata into live data?

class QuizViewModel(application: Application, val hashParams:HashMap<String,String?>) :  AndroidViewModel( application) {

    private lateinit var quizDataList:MutableList<QuizData>
    var currentAnswerSet = MutableLiveData<List<QuizData.Answer>>()
    var currentQuestion = MutableLiveData<String>()
    var count = MutableLiveData(0)

    //Repository
    suspend fun getApiData(){

     quizDataList = QuizDataRepository.apiService.getQuizData(hashParams).toMutableList()
        }

    fun loadData(){
    //    currentAnswerSet.(quizDataList.first().answerArray)
        currentAnswerSet.value = quizDataList.first().answerArray
        currentQuestion.value = quizDataList.first().question
        quizDataList.removeFirst()
    }
    fun checkAnswer(position: Int) {

    }
/*
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

    }*/


}