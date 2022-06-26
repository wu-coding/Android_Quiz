package com.example.clean_quiz.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clean_quiz.data.User
import com.example.clean_quiz.data.models.FullRecord
import com.example.clean_quiz.data.models.RecordPreferences
import com.example.clean_quiz.data.models.SearchPreferences
import com.example.clean_quiz.data.repository.ApiDataRepository_Impl
import com.example.clean_quiz.data.repository.QuizDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
// user records doesnt show afrt search

@HiltViewModel
class ResultsViewModel @Inject constructor(
    private val quizDataRepository: QuizDataRepository
) : ViewModel() {
    // ResultsFragments
    val tabNumber = MutableLiveData<Int>()
    val temp = "Test"

    // Score Fragment
    lateinit var top10RecordList: Array<FullRecord>
    lateinit var userRecordList: Array<FullRecord>
    lateinit var totalRecordList: Array<FullRecord>

    // Search Fragment
    lateinit var searchResultsList: Array<FullRecord>
    lateinit var searchInput: SearchPreferences

    //Test Data
    fun loadUser() {
        quizDataRepository.userData = User(0, "Sam", "Two")
        quizDataRepository.userPrefData = RecordPreferences(0, 0, "Linux", "easy", 2)
    }


    fun loadScoreData() {
        loadUser()
        top10RecordList = quizDataRepository.getTop10()
        userRecordList = quizDataRepository.getUserRecords(
            quizDataRepository.userData.firstName,
            quizDataRepository.userData.lastName
        )
        totalRecordList =
            quizDataRepository.getCategoryRecords(quizDataRepository.userPrefData.category)
    }

    fun loadSearchData() {
        searchInput = SearchPreferences("Sam", "Two", "","")
        searchResultsList = quizDataRepository.searchRecordsQuery(searchInput)
        val temp = "sudo"
    }


    fun getUserId():Int{
        return quizDataRepository.userPrefData.user_id
    }

    fun getRecordId():Int{
        return quizDataRepository.userPrefData.record_id
    }
}