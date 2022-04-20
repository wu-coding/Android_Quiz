package com.example.clean_quiz.ui.viewmodel

import android.app.Application
import android.content.Context
import android.graphics.Color
import android.text.method.TextKeyListener.clear
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
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

//import com.example.clean_quiz.Answer

// maybe convert quizdata into live data?

class QuizViewModel(application: Application, val hashParams:HashMap<String,String?>) :  AndroidViewModel( application) {

    // backgroundColors = MutableLiveData<Array<Int>>(Array(currentCorrect.size){R.color.white})

    val userScore = Score(application)
    val progress = MutableLiveData<Int>(0)
    lateinit var quizDataList: MutableList<QuizData>

    val currentQuestion = MutableLiveData<String>() //test livedata

    lateinit var currentCorrect: List<Boolean>

    val currentAnswerSet = ArrayList<String>(6)

    val backgroundColors = MutableLiveData<Array<Int>>()

    val imageType = ArrayList<Int?>(6)

    val userInput = mutableSetOf<Int>()

    var updatePos = 0


    //Repository
    suspend fun getApiData() {
        quizDataList = QuizDataRepository.apiService.getQuizData(hashParams).toMutableList()
    }

fun clearData(){
    currentAnswerSet.clear()
   // backgroundColors.value?.set(null)
    imageType.clear()
    updatePos = 0
    userInput.clear()

}
    fun loadData() {
      //  userScore.start()
        //also need to fix livedata reseting?

        currentQuestion.value = quizDataList.first().question
        currentCorrect = quizDataList.first().correctAnswers
        currentAnswerSet.addAll(quizDataList.first().answerList)
        backgroundColors.value = Array(currentCorrect.size){R.color.white}
        imageType.addAll(Collections.nCopies(currentCorrect.size, null))

        quizDataList.removeFirst()
    }


    fun checkAnswers():Boolean {
// we can use a stack to keep track of updated values?
        var testAnswers: Boolean = true
        for ((i, value) in currentCorrect.withIndex()) {
            if (value) {
                if (userInput.contains(i)) {
                    imageType[i] = R.drawable.correct_answer
                    userInput.remove(i)
                } else {
                    imageType[i] =  R.drawable.correct_answer
                    backgroundColors.value?.set(i, R.color.yellow)
                    testAnswers = false
                }
            }
        }

        if(userInput.size > 0){
            for(i in userInput){
       //         userInput.remove(i)
                imageType[i] = R.drawable.wrong_answer
                backgroundColors.value?.set(i, R.color.red)
            }
        }
     //   userScore.updateScore(testAnswers)
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
            backgroundColors.value = backgroundColors.value

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