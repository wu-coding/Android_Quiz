package com.example.clean_quiz.data.repository

import com.example.clean_quiz.data.DAO.*
import com.example.clean_quiz.data.User
import com.example.clean_quiz.data.models.Database
import com.example.clean_quiz.data.models.Record
import com.example.clean_quiz.data.models.RecordPreferences
import com.example.clean_quiz.data.models.RecordScore
import javax.inject.Inject

class QuizDataRepository @Inject constructor(
    private val dataBase: Database,
    private val userDao: UserDao,
    private val recordDao: RecordDao
) {

    fun getUser(fname: String, lname: String): Int = userDao.findUser(fname, lname)
    fun insertUser(user: User): Long = userDao.insertUser(user)
    fun getRecord(record_ID: Int): Record = recordDao.loadApiParams(record_ID)

    fun updateRecordPreferences(recordPreferences: RecordPreferences) = recordDao.updateRecordPreferences(recordPreferences)
    fun updateRecordScore(recordScore: RecordScore) = recordDao.updateRecordScore(recordScore)
  //  fun insertRecord(record: Record): Long = recordDao.insertRecord(record)

    suspend fun clearAllDataBase() {
        dataBase.clearAllTables()
    }


    private fun checkUserExist(userId: Int): Boolean {
        return userId == 0
    }

     fun createUser(fname: String, lname: String): Long {
        val userId = getUser(fname, lname)
        return if (checkUserExist(userId)) {
            userId.toLong()
        } else {
            insertUser(User(0, fname, lname))
        }
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


    suspend fun loadApiParam(record_ID: Int): HashMap<String, String> {
        val tempRecord = getRecord(record_ID)
        return hashMapOf<String, String>(
            "category" to tempRecord.category,
            "difficulty" to tempRecord.difficulty,
            "limit" to tempRecord.questionAmount.toString(),
            "apiKey" to "hcUZqLCh8uTaXt121DQd5IQ7wv5GFIVA5YlaPxy4"
        )
    }


}