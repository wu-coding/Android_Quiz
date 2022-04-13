package com.example.clean_quiz.ui.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
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


    lateinit var quizDataList: MutableList<QuizData>
    val userScore = Score(application)
    var currentAnswerSet = MutableLiveData<List<String>>()
    var currentQuestion = MutableLiveData<String>()

    //   private lateinit var userChoices:Array<Boolean>
    lateinit var currentCorrect: List<Boolean>
    private lateinit var userChoices:MutableList<Boolean>

    //lateinit var userChoices:ArrayList<Boolean>
    var position = MutableLiveData<Int>()
    var progress = MutableLiveData<Int>(0)

    val userInput = mutableSetOf<Int>()

    //Repository
    suspend fun getApiData() {

        quizDataList = QuizDataRepository.apiService.getQuizData(hashParams).toMutableList()
    }

    fun loadData() {

        userScore.start()
        currentQuestion.value = quizDataList.first().question
        currentAnswerSet.value = quizDataList.first().answerList
        currentCorrect = quizDataList.first().correctAnswers
        userChoices = ArrayList(quizDataList.first().userSelectedAnswers)
        quizDataList.removeFirst()
    }

    fun checkAnswers() {
        var testAnswers: Boolean = true

        // for loop in all correct answers
        // search for value if then
        currentCorrect.mapIndexed { index, b ->
            if(b && userArray.contains(index)){
                userArray[index]
            }else{
                score --
                //have correc answers?
            }
        }

        // Need to remove user array duplicates?


    // go though userArray
        // check
        for(i in currentCorrect){
            if (currentCorrect.get())
            if (userArray.contains() )
        }
        for (i in userArray) {

            if ( currentCorrect[i]) {
                // call this shit?
                // edit image array?
            }else{
                testAnswers = false
            }
        }
        userScore.updateScore(testAnswers)
        userScore.stop()
    }

    val getUserCheck = { pos: Int,  ->
        userChoices[pos]
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