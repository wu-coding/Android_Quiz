package com.example.android_quiz.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android_quiz.data.User

@Dao
interface UserDao {
    @Query("Select * From user where first_name = :fname AND last_name = :lname")
    fun findUser(fname:String, lname:String):User?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertUser(user: User):Long
}