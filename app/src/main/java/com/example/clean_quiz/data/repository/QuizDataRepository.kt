package com.example.clean_quiz.data.repository

import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.clean_quiz.data.DAO.*
import com.example.clean_quiz.data.User
import com.example.clean_quiz.data.models.*
import com.example.clean_quiz.utils.addQueryParam
import com.example.clean_quiz.utils.addQuotes
import java.sql.SQLData
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap
import kotlin.properties.Delegates

class QuizDataRepository @Inject constructor(
    private val dataBase: Database,
    private val userDao: UserDao,
    private val recordDao: RecordDao
) {

    lateinit var userData: User
    lateinit var userPrefData: RecordPreferences
    lateinit var userScoreData: RecordScore

    fun getUser(fname: String, lname: String) {
        val userValue = userDao.findUser(fname, lname)

        if (userValue != null) {
            userData = userValue
        } else {
            userData = User(0, fname, lname)
            insertUser(userData)
        }
    }

    fun getUserRecords(firstName:String, lastName:String) = recordDao.getUserRecords(firstName, lastName)
    fun insertUser(user: User) = userDao.insertUser(user)
    fun updateRecordScore(recordScore: RecordScore) = recordDao.updateRecordScore(recordScore)
    fun getTop10() = recordDao.getTop10()
    fun getCategoryRecords(categoryID: String) = recordDao.getCategoryRecords(categoryID)


    //Get rid of Long?
    fun updateRecordPreferences(recordPreferences: RecordPreferences): Long {
        userPrefData = recordPreferences
        userPrefData.user_id = userData.userId
      //  val temp = userPrefData.isInitialized
        return recordDao.updateRecordPreferences(userPrefData)
    }


    fun clearAllDataBase() {
        dataBase.clearAllTables()
    }


    fun searchRecordsQuery(searchValue: SearchPreferences) = recordDao.searchRecordsQuery(searchRecordsParam(searchValue))

    // Should maybe make class nullable instead?
    fun searchRecordsParam(searchValue: SearchPreferences):SimpleSQLiteQuery {

        var queryString =
            "Select * From record" + " INNER JOIN user ON record.user_id = user.user_id "

        var queryArgs = StringBuilder()

        var storeQueryParams:Queue<String> = LinkedList<String>()


        storeQueryParams.add(
                "first_name == ".addQuotes(searchValue.first_name)

        )
        storeQueryParams.add(
                "last_name == ".addQuotes(searchValue.last_name)

        )
       /* storeQueryParams.add(
            "category == \"linux\""
             //   "record.category == " + searchValue.category

        )
        storeQueryParams.add(
            "difficulty == \"easy\""
              //  "record.difficulty == " + searchValue.difficulty
            )*/

        for(i in 1..storeQueryParams.size){
            queryArgs.append(storeQueryParams.remove())
            if(storeQueryParams.isNotEmpty()) {
                queryArgs.append(" AND ")
            }
        }

      /*  for( i in storeQueryParams){
            queryArgs.append(storeQueryParams.remove())

            if(storeQueryParams.size > 1) queryArgs.append(" ADD ")
        }
*/
        if(queryString != ""){
            queryString += " WHERE " + queryArgs
        }

        return SimpleSQLiteQuery(queryString)
        }

    fun testTrue(testValue: String): Boolean {
        if (testValue != "") {
            return true
        }
        return false
    }
    /* suspend fun createRecord(
         userId: Int,
         category: String,
         difficulty: String,
         questionAmount: Int
     ): Long {
         val tempRecord = Record(
             0, userId, category, difficulty, questionAmount,
             0, 0, 0
         )
         return insertRecord(tempRecord)
     }*/


    fun loadApiParam(): HashMap<String, String> {
        val temp = "sudo "
        return hashMapOf<String, String>(
            "category" to userPrefData.category!!,
            "difficulty" to userPrefData.difficulty!!,
            "limit" to userPrefData.question_amount.toString(),
            "apiKey" to "hcUZqLCh8uTaXt121DQd5IQ7wv5GFIVA5YlaPxy4"
        )
    }

}