package com.example.clean_quiz.ui.viewmodel

import android.content.Context
import android.widget.Chronometer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clean_quiz.R

import com.example.clean_quiz.data.models.Card
import com.example.clean_quiz.data.repository.ApiDataRepository
import com.example.clean_quiz.data.repository.ApiDataRepository_Impl
import com.example.clean_quiz.data.repository.QuizDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel

import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

//import com.example.clean_quiz.Answer

// maybe convert quizdata into live data?

@HiltViewModel
class QuizViewModel @Inject constructor( private val quizDataRepository: QuizDataRepository, private val apidatarepositoryImpl: ApiDataRepository_Impl): ViewModel() {

    // backgroundColors = MutableLiveData<Array<Int>>(Array(currentCorrect.size){R.color.white})

   // val userScore = Score(application)
    val progress = MutableLiveData<Int>(0)
    lateinit var cardList: MutableList<Card>

    val currentQuestion = MutableLiveData<String>() //test livedata

    lateinit var currentCorrect: List<Boolean>

    val currentAnswerSet = ArrayList<String>(6)

    val backgroundColors = MutableLiveData<Array<Int>>()

    val imageType = ArrayList<Int?>(6)

    val userInput = mutableSetOf<Int>()

    var updatePos = 0


    suspend fun loadData(apiParams:HashMap<String,String>) {
        val apiData = apidatarepositoryImpl.getApiData(apiParams)
        init()
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

        currentQuestion.value = cardList.first().question
        currentCorrect = cardList.first().correctAnswers
        currentAnswerSet.addAll(cardList.first().answerList)
        backgroundColors.value = Array(currentCorrect.size){R.color.white}
        imageType.addAll(Collections.nCopies(currentCorrect.size, null))

        cardList.removeFirst()
    }


    fun checkAnswers():Boolean {
// we can use a livedata stack to keep track of updated values?
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