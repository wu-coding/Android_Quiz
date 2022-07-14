package com.example.clean_quiz.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clean_quiz.data.User
import com.example.clean_quiz.models.RecordPreferences
import com.example.clean_quiz.repository.QuizDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(private val quizDataRepository: QuizDataRepository) :
    ViewModel() {

    var currentUser = User(0, "", "")
    var currentPreferences = RecordPreferences(0, 0, null, null, 0)

    fun clearDatabase() {
        quizDataRepository.clearAllDataBase()
    }

    fun writeToDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            quizDataRepository.updateQuizPreferences(currentUser, currentPreferences)
        }
    }
}
