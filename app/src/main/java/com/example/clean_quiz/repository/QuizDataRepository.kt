package com.example.clean_quiz.repository

import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.clean_quiz.DAO.RecordDao
import com.example.clean_quiz.DAO.UserDao
import com.example.clean_quiz.data.User
import com.example.clean_quiz.models.*
import com.example.clean_quiz.utils.addQueryParam
import java.util.*
import javax.inject.Inject

class QuizDataRepository @Inject constructor(
    private val dataBase: Database,
    private val userDao: UserDao,
    private val recordDao: RecordDao
) {

    lateinit var userData: User
    lateinit var userPrefData: RecordPreferences

    fun getUserRecords(firstName: String, lastName: String) =
        recordDao.getUserRecords(firstName, lastName)

    private fun insertUser(user: User) = userDao.insertUser(user)
    fun getTop10() = recordDao.getTop10()
    fun getCategoryRecords(categoryID: String) = recordDao.getCategoryRecords(categoryID)

    fun updateRecordScore(recordScore: QuizScore) {
        val userScore = RecordScore(userPrefData.record_id, recordScore)
        recordDao.updateRecordScore(userScore)
    }


    fun updateQuizPreferences(userParam: User, recordPreferences: RecordPreferences) {
        // check for existing user
        val userValue = userDao.findUser(userParam.firstName, userParam.lastName)

        // Store User in DB and Repo
        if (userValue != null) {
            userData = userValue!!
        } else {
            val userId = insertUser(userParam).toInt()
            userData = User(userId, userParam.firstName, userParam.lastName)
        }

        //Store User Pref in Record
        userPrefData = recordPreferences
        userPrefData.user_id = userData.userId

        //Create Record
        val recordID = recordDao.updateRecordPreferences(userPrefData)
        userPrefData.record_id = recordID.toInt()

    }


    fun clearAllDataBase() {
        dataBase.clearAllTables()
    }

    fun searchRecordsQuery(searchValue: SearchPreferences) =
        recordDao.searchRecordsQuery(searchRecordsParam(searchValue))


    fun searchRecordsParam(searchValue: SearchPreferences): SimpleSQLiteQuery {

        var queryString =
            "Select * From record" + " INNER JOIN user ON record.user_id = user.user_id "

        var queryArgs = StringBuilder()

        var storeQueryParams = LinkedList<String?>()


        storeQueryParams.add(
            addQueryParam("first_name", searchValue.first_name)
            //   "first_name == ".addQuotes(searchValue.first_name)

        )
        storeQueryParams.add(
            addQueryParam("last_name", searchValue.last_name)
            //      "last_name == ".addQuotes(searchValue.last_name)
        )
        storeQueryParams.add(
            addQueryParam("record.category", searchValue.category)
            //   "record.category == " + searchValue.category
        )
        storeQueryParams.add(
            addQueryParam("record.difficulty", searchValue.difficulty)
            //  "record.difficulty == " + searchValue.difficulty
        )

        for (i in storeQueryParams) {
            var queryValue = ""
            if (i != null) {
                if (queryArgs.isNotEmpty()) {
                    queryValue = " AND " + i
                } else {
                    queryValue = i
                }
                queryArgs.append(queryValue)
            }
        }

        if (queryString != "") {
            queryString += " WHERE " + queryArgs
        }

        return SimpleSQLiteQuery(queryString)
    }


    fun loadApiParam(): HashMap<String, String> {
        return hashMapOf<String, String>(
            "tags" to userPrefData.category!!,
            "difficulty" to userPrefData.difficulty!!,
            "limit" to userPrefData.question_amount.toString(),
            "apiKey" to "hcUZqLCh8uTaXt121DQd5IQ7wv5GFIVA5YlaPxy4"
        )
    }

}