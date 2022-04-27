package com.example.clean_quiz.ui.viewmodel


import android.widget.*

import androidx.lifecycle.ViewModel
import com.example.clean_quiz.data.User

class StartViewModel: ViewModel() {
    var userData: User = User()

    var name = ""
    var category = "Select a Category"
    var difficulty = -1
    var questionAmount = ""
    var errorOutput = ""

// name length = 0
    // just make it a toast?

    fun validateResponse() {
        errorOutput = ""
        var errorMessage = ""

        if (name == "") errorMessage += " name"
        if (category == "Select a Category") errorMessage += " category"
        if (difficulty == -1) errorMessage += " difficulty"
        if (questionAmount == "") errorMessage += " question amount"

        if (errorMessage.length > 0){
            errorOutput = "Please fill out the empty section:" + errorMessage
        }
    }

    fun test(){
        val temp = 0
    }
/*    fun validateUserName(){}
    fun validateCategory(){}
    fun validateDifficulty(){}
    fun validateQuestionAmount(){}*/


    fun radioCallback(radioGroup: RadioGroup, radioButtonID: Int) {
        val radioButton = radioGroup.findViewById<RadioButton>(radioButtonID)
        userData.difficutly = radioButton.text.toString()
    }

    fun questAmountCallback(text: CharSequence) {
        userData.questionAmount = text.toString().toInt()
    }

    fun userNameCallback(text: CharSequence) {
        userData.name = text.toString()
    }

  /*  fun validateResponse():Boolean {
        // Fix implementation to notify individual fields of mistakes
        return !( listOf(
            userData.name,
            userData.difficutly,
            userData.questionAmount,
            userData.name
        ).any { it == null}
                && userData.difficutly != "Select a Category"
                && userData.questionAmount in 1..20)
    }*/

    fun convertUserChoice():HashMap<String,String?> {

    /*    var userParams = bundleOf()
        userParams.putSerializable("category", "Linux")
        userParams.putSerializable("difficulty", userData.difficutly,)
        userParams.putSerializable("limit", userData.questionAmount)
        return userParams
    */
        return hashMapOf( "category" to userData.category,
            "difficulty" to userData.difficutly,
            "limit" to userData.questionAmount.toString(),
            "apiKey" to "hcUZqLCh8uTaXt121DQd5IQ7wv5GFIVA5YlaPxy4")
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
