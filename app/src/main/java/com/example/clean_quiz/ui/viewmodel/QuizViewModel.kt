package com.example.clean_quiz.ui.viewmodel

import android.app.Application
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.Chronometer
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat.getDrawable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.clean_quiz.R
import com.example.clean_quiz.data.Score
import com.example.clean_quiz.data.models.QuizData
import com.example.clean_quiz.data.repository.QuizDataRepository
import com.example.clean_quiz.ui.views.QuizFragment
import kotlinx.coroutines.async
import kotlinx.coroutines.currentCoroutineContext

import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//import com.example.clean_quiz.Answer

// maybe convert quizdata into live data?

class QuizViewModel(application: Application, val hashParams:HashMap<String,String?>) :  AndroidViewModel( application) {



    val userScore = Score(application)
    var progress = MutableLiveData<Int>(0)
    lateinit var quizDataList: MutableList<QuizData>

    var currentQuestion = MutableLiveData<String>() //test livedata

    lateinit var currentCorrect: List<Boolean>
    lateinit var currentAnswerSet:List<String>

   // lateinit var backgroundColors:MutableLiveData<ArrayList<Int>>
    lateinit var backgroundColors:MutableLiveData<Array<Int>>
    lateinit var imageType:MutableLiveData<Array<Int>>
    val userInput = mutableSetOf<Int>()

    var updatePos = 0

    //Repository
    suspend fun getApiData() {

        quizDataList = QuizDataRepository.apiService.getQuizData(hashParams).toMutableList()
    }

    fun loadData() {
     //   userScore.start()
        userInput.clear()
        currentQuestion.value = quizDataList.first().question
        currentCorrect = quizDataList.first().correctAnswers
        currentAnswerSet = quizDataList.first().answerList

        // cant be int
        backgroundColors = MutableLiveData<Array<Int>>(Array(currentCorrect.size){R.color.white})
        val temp = R.color.white
        val tempdo = backgroundColors.value?.get(0)
        imageType =  MutableLiveData<Array<Int>>(Array(currentCorrect.size){ View.INVISIBLE})

        quizDataList.removeFirst()
    }


    fun checkAnswers():Boolean {
// we can use a stack to keep track of updated values?
        var testAnswers: Boolean = true
        for ((i, value) in currentCorrect.withIndex()) {
            if (value) {
                if (userInput.contains(i)) {
                    imageType.value?.set(i, R.drawable.correct_answer)
                    userInput.remove(i)
                } else {
                    imageType.value?.set(i, R.drawable.correct_answer)

                    backgroundColors.value?.set(i, R.color.yellow)
                    testAnswers = false
                }
            }

            if(userInput.size > 0){
                for(i in userInput){
                    userInput.remove(i)
                       imageType.value?.set(i, R.drawable.wrong_answer)
                       backgroundColors.value?.set(i, R.color.red)
                }
            }
        }
        return testAnswers
    }

        // check view visible or not?
        val getUserInput = { pos:Int ->
            if (userInput.contains(pos)){
                backgroundColors.value?.set(pos, R.color.white)
                userInput.remove(pos)
            }else{
                userInput.add(pos)
                backgroundColors.value?.set(pos,  R.color.light_blue_600)
            }
            updatePos = pos
            val temp = backgroundColors.value?.clone()
            backgroundColors.value = temp!!

        }
    }



class Score(context:Context){
    val timeTaken = Chronometer(context)

    var correct:Int = 0
    var wrong:Int=0

    fun updateScore(test:Boolean){ if (test) correct++ else wrong--}
    fun start(){timeTaken.start()}
    fun stop(){timeTaken.stop()}
}