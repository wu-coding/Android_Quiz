package com.example.clean_quiz.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clean_quiz.models.FullRecord
import com.example.clean_quiz.models.SearchPreferences
import com.example.clean_quiz.repository.QuizDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

// user records doesnt show afrt search

@HiltViewModel
class ResultsViewModel @Inject constructor(
    private val quizDataRepository: QuizDataRepository
) : ViewModel() {
    // ResultsFragments
    val tabNumber = MutableLiveData<Int>()


    // Score Fragment
    lateinit var top10RecordList: Array<FullRecord>
    lateinit var userRecordList: Array<FullRecord>
    lateinit var totalRecordList: Array<FullRecord>

    // Search Fragment
    var searchResultsList = MutableLiveData<Array<FullRecord>>()
    var searchInput = SearchPreferences(null,null,null,null)

    fun loadScoreData() {
        top10RecordList = quizDataRepository.getTop10()
        userRecordList = quizDataRepository.getUserRecords(
            quizDataRepository.userData.firstName,
            quizDataRepository.userData.lastName
        )
        totalRecordList =
            quizDataRepository.getCategoryRecords(quizDataRepository?.userPrefData?.category!!)
    }

    fun loadSearchData() {
        searchResultsList.postValue(quizDataRepository.searchRecordsQuery(searchInput))

    }


    fun getUserId():Int{
        return quizDataRepository.userPrefData.user_id
    }

    fun getRecordId():Int{
        return quizDataRepository.userPrefData.record_id
    }

}