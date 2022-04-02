package com.example.clean_quiz.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.clean_quiz.ui.views.QuizFragment
import java.util.HashMap

class QuizViewModelFactory(val application: QuizFragment, val hash:HashMap<String,String?>) : ViewModelProvider.AndroidViewModelFactory(application
) {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QuizViewModel(application,hash) as T
    }
}
