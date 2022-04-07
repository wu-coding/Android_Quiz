package com.example.clean_quiz.ui.viewmodel


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.os.bundleOf
import androidx.databinding.BindingAdapter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clean_quiz.data.User
import com.example.clean_quiz.data.repository.QuizDataRepository
import com.example.clean_quiz.ui.views.MainActivity
import com.google.gson.JsonElement
import com.squareup.moshi.Json
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import org.json.JSONObject

class StartViewModel: ViewModel() {
    var userData: User = User()


    val spinnerListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
           // fix this
            val temp = view as TextView
            userData.category = temp.text as String?
        }
        override fun onNothingSelected(p0: AdapterView<*>?) {}
    }

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

    fun validateResponse():Boolean {
        // Fix implementation to notify individual fields of mistakes
        return !( listOf(
            userData.name,
            userData.difficutly,
            userData.questionAmount,
            userData.name
        ).any { it == null}
                && userData.difficutly != "Select a Category"
                && userData.questionAmount in 1..20)
    }

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
