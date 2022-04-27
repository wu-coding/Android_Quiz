package com.example.clean_quiz.data.DAO

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface UserDao {
    @Query("Select Count(*) From user where first_name = :fname AND last_name = :lname")
    fun findUser(fname:String, lname:String):Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(vararg user:User)
}