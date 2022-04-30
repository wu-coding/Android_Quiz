package com.example.clean_quiz.data.repository

import com.example.clean_quiz.data.DAO.*
import com.example.clean_quiz.data.User
import com.example.clean_quiz.data.models.Database
import com.example.clean_quiz.data.models.Record
import javax.inject.Inject

class QuizDataRepository @Inject constructor(private val dataBase:Database, private val userDao:UserDao, private val recordDao:RecordDao){

    suspend fun findUser(fname:String, lname:String):Int = userDao.findUser(fname, lname)
    suspend fun insertUser( user: User): Long = userDao.insertUser(user)

    suspend fun loadApiParams(record_ID:Int): Record = recordDao.loadApiParams(record_ID)
    suspend fun insertRecord(vararg record: Record) = recordDao.insertRecord(*record)

    suspend fun clearAllDataBase(){
        dataBase.clearAllTables()
    }

    suspend fun writeUser(fname:String, lname:String):Int{
        var getUser = findUser(fname,lname)
        if(getUser == 0){
            getUser = insertUser(User(0,fname,lname)) as Int
        }
        return getUser
    }

}