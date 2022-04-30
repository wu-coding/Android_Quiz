package com.example.clean_quiz.data.models

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.clean_quiz.data.DAO.RecordDao
import com.example.clean_quiz.data.DAO.UserDao
import com.example.clean_quiz.data.User

@Database(entities = [User::class, Record::class] , version = 1 )
abstract class Database:RoomDatabase() {
    abstract fun UserDao(): UserDao
    abstract fun RecordDao(): RecordDao

}