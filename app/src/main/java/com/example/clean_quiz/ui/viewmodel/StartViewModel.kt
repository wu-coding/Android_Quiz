package com.example.clean_quiz.ui.viewmodel


import androidx.lifecycle.ViewModel
import com.example.clean_quiz.data.User
import com.example.clean_quiz.data.models.FullRecord
import com.example.clean_quiz.data.models.RecordPreferences
import com.example.clean_quiz.data.repository.ApiDataRepository_Impl
import com.example.clean_quiz.data.repository.QuizDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(private val quizDataRepository: QuizDataRepository) :
    ViewModel() {

    var currentUser = User(1, "Sam", "Two")
    var currentPreferences = RecordPreferences(0, 1, "Linux", "easy", 2)
    // var currentPreferences = RecordPreferences(0,0,"","",0)


    var errorOutput = ""

    fun validateResponse() {
        errorOutput = ""
        var errorMessage = ""

        if (currentUser.firstName == "") errorMessage += " first name"
        if (currentUser.lastName == "") errorMessage += " last name"
        if (currentPreferences.category == "Select a Category") errorMessage += " category"
        if (currentPreferences.difficulty == "") errorMessage += " difficulty"
        if (currentPreferences.question_amount.toString() == "") errorMessage += " question amount"

        if (errorMessage.length > 0) {
            errorOutput = "Please fill out the empty section:" + errorMessage
        }
    }


    suspend fun clearDatabase() {
        quizDataRepository.clearAllDataBase()
    }

    //somehow has problems when starting from empty db? User problems?
    suspend fun writeToDatabase() {
        quizDataRepository.getUser(currentUser.firstName, currentUser.lastName)
        quizDataRepository.updateRecordPreferences(currentPreferences)
    }

}


/*
else{
    val sendParams = mapOf("category" to userData.category, "difficulty" to userData.name, "limit" to userData.questionAmount)
    val result = viewModelScope.async(Dispatchers.IO) {
        QuizDataRepository.apiService.getQuizData(sendParams as Map<String, String>) }
    viewModelScope.launch {
        test = async(Dispatchers.IO) {QuizDataRepository.apiService.getQuizData(sendParams as Map<String, String>) }

        result.await()

    }
    test.await()}rSequence!;&gt"*/
