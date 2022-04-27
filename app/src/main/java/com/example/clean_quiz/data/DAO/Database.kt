package com.example.clean_quiz.data.DAO

import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase

@Database(entities = [User::class, Record::class] , version = 1 )
abstract class Database:RoomDatabase() {
    abstract fun UserDao():UserDao
    abstract fun RecordDao():RecordDao
}