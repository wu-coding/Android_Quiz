package com.example.android_quiz.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_quiz.models.*
import com.example.android_quiz.repository.ApiDataRepository_Impl
import com.example.android_quiz.repository.QuizDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CardUiState(
    var answerSelected: Boolean,
    var answerUpdated: Boolean,
    var answerStatus: AnswerCheckStatus?

)


@HiltViewModel
class QuizViewModel @Inject constructor(
    private val quizDataRepository: QuizDataRepository,
    private val apidatarepositoryImpl: ApiDataRepository_Impl
) : ViewModel() {
    val currentScore = QuizScore()
    val showScore = MutableLiveData<Int>(0)
    val progress = MutableLiveData<Int>(0)
    var questionAmount = MutableLiveData<Int>()
    val currentQuestion = MutableLiveData<String>("")
    lateinit var currentQuestionAnswerSet: QuestionAnswerSet
    var cardUiStateList = ArrayList<CardUiState>()
    lateinit var apiData: MutableList<QuestionAnswerSet>
    var apiResponse = MutableLiveData<ApiResult<Any?>>()
    var currentAnswerList = listOf<String>()
    var userInput = mutableSetOf<Int>()
    var correctSet = setOf<Int>()


    fun loadApiData() {
        viewModelScope.launch(Dispatchers.IO) {
            val apiParams = quizDataRepository.loadApiParam()
            apidatarepositoryImpl.getApiData(apiParams).collect { value ->
                apiResponse.postValue(value)
            }
        }
    }

    fun createCardStateList() {
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


    fun loadNextQuestionSet() {
        currentQuestionAnswerSet = apiData.last()
        currentQuestion.value = currentQuestionAnswerSet.question
        currentAnswerList = currentQuestionAnswerSet.answerList
        correctSet = currentQuestionAnswerSet.correctSet
        createCardStateList()
        questionAmount.setValue(questionAmount.value?.minus(1))
    }

    fun clearQuestionSet() {
        apiData.removeLast()
        cardUiStateList.removeAll(cardUiStateList)
        userInput.clear()

        showScore.value = currentScore.totalScore
        progress.value = progress.value?.plus(1)
    }


    fun submitAnswers(): Set<Int> {
        val updatedRows = userInput + correctSet

        for (i in updatedRows) {
            cardUiStateList[i].answerUpdated = true
            cardUiStateList[i].answerStatus = checkAnswerRow(i)
        }
        calculateScore()
        return updatedRows
    }


    fun checkAnswerRow(pos: Int): AnswerCheckStatus {
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

    private fun calculateScore() {
        if (correctSet.equals(userInput)) {
            currentScore.updateScore(true)
        } else {
            currentScore.updateScore(false)
        }
    }

    fun writeToDatabase() {
        quizDataRepository.updateRecordScore(currentScore)
    }
}
