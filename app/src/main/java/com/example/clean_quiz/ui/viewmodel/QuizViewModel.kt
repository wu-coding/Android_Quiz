package com.example.clean_quiz.ui.viewmodel

import android.content.Context
import android.widget.Chronometer
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clean_quiz.data.models.AnswerCheckStatus

import com.example.clean_quiz.data.models.QuestionAnswerSet
import com.example.clean_quiz.data.models.RecordScore
import com.example.clean_quiz.data.repository.ApiDataRepository_Impl
import com.example.clean_quiz.data.repository.QuizDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel

import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

//import com.example.clean_quiz.Answer

// maybe convert quizdata into live data?

data class CardUiState(
    var answerSelected:Boolean,
    var answerUpdated:Boolean,
    var answerStatus: AnswerCheckStatus?

  //  val storeInput: (Int) -> Unit,
   // val checkRow: (Int) -> AnswerCheckStatus
)


@HiltViewModel
class QuizViewModel @Inject constructor(
    private val quizDataRepository: QuizDataRepository,
    private val apidatarepositoryImpl: ApiDataRepository_Impl
) : ViewModel() {
    // val userScore = Score(application)
    val currentScore = Score()
    val showScore = MutableLiveData<Int>(0)
    val progress = MutableLiveData<Int>(0)
    var questionAmount = MutableLiveData<Int>()
    val currentQuestion = MutableLiveData<String>("")

    lateinit var currentQuestionAnswerSet: QuestionAnswerSet

    var cardUiStateList =  ArrayList<CardUiState>()

    lateinit var apiData:MutableList<QuestionAnswerSet>

    var currentAnswerList = listOf<String>()

    var userInput = mutableSetOf<Int>()

    var correctSet = setOf<Int>()

    suspend fun loadApiData(rowID: Long) {
        val apiParams = quizDataRepository.loadApiParam(rowID.toInt())
        apiData = apidatarepositoryImpl.getApiData(apiParams).toMutableList()
        questionAmount.postValue(apiData.size)
    }

    /*fun init() {
     //   currentQuestionAnswerSet = QuestionAnswerSet(null,null,null)
        loadNextQuestionSet()
        currentScore = Score()
        for (answer in currentAnswerList) {
            cardUiStateList.add(
                CardUiState(
                    answerSelected = false,
                    answerUpdated = false,
                    answerStatus = null
                )
            )
        }
    }*/

    fun createCardStateList(){
        for (answer in currentAnswerList) {
            cardUiStateList.add(
                CardUiState(
                    answerSelected = false,
                    answerUpdated = false,
                    answerStatus = null
                )
            )
        }
    }


    fun loadNextQuestionSet(){
        currentQuestionAnswerSet = apiData.last()
        currentQuestion.value = currentQuestionAnswerSet.question
        currentAnswerList = currentQuestionAnswerSet.answerList
        correctSet = currentQuestionAnswerSet.correctSet
        createCardStateList()
        questionAmount.setValue(questionAmount.value?.minus(1))
    }

    fun clearQuestionSet(){
        apiData.removeLast()
        cardUiStateList.removeAll(cardUiStateList)
        userInput.clear()

        showScore.value = currentScore.totalScore
        progress.value = progress.value?.plus(1)}


    fun submitAnswers(): Set<Int> {
        val updatedRows = userInput + correctSet

        for(i in updatedRows){
            cardUiStateList[i].answerUpdated = true
            cardUiStateList[i].answerStatus = checkAnswerRow(i)
        }
        calculateScore()
        return updatedRows
    }


    fun checkAnswerRow(pos: Int): AnswerCheckStatus {
        val temp = userInput.contains(pos)
        val temp2 = correctSet.contains(pos)
        if (userInput.contains(pos) && correctSet.contains(pos)) {
            return AnswerCheckStatus.CORRECT
        } else if (userInput.contains(pos)) {
            return AnswerCheckStatus.WRONG
        } else {
            return AnswerCheckStatus.NOTSELECTED
        }
    }

    val storeUserInput: (Int) -> Unit = { pos: Int ->
        if (!userInput.contains(pos)) {
            userInput.add(pos)
        } else {
            userInput.remove(pos)
        }
    }

    fun calculateScore() {
        if (correctSet.equals(userInput)) {
            currentScore.correct ++
        } else {
            currentScore.wrong ++
        }
    }

    fun writeToDatabase(){
     val scoreParam = RecordScore(0,currentScore.correct,currentScore.wrong,currentScore.timeTaken)
    quizDataRepository.updateRecordScore()
    }
}


//class Score(context: Context) {
    class Score(var correct: Int = 0,
                var wrong: Int = 0,
                var totalScore: Int = 0,
                var timeTaken:Int =0) {

    fun updateTime(timeParam:String){
        timeTaken = timeParam.toInt()
    }

    fun updateScore(score:Boolean){
        if(score){
            correct++
        }else{
            wrong++
        }
        if(correct > wrong){
            totalScore = correct - wrong
        }else{
            totalScore = 0
        }
    }

    // maybe after each press calc time?
  /*  fun start() {
        timeTaken.start()
    }

    fun stop() {
        timeTaken.stop()
    }*/
}