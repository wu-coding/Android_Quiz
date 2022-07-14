package com.example.clean_quiz.models

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.clean_quiz.DAO.RecordDao
import com.example.clean_quiz.DAO.UserDao
import com.example.clean_quiz.data.User

@Database(entities = [User::class, Record::class] , version = 1 )
abstract class Database:RoomDatabase() {
    abstract fun UserDao(): UserDao
    abstract fun RecordDao(): RecordDao

}