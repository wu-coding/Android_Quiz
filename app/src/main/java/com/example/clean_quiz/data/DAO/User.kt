package com.example.clean_quiz.data.DAO

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class User(
    @PrimaryKey (autoGenerate = true) val userId:Int,
    @ColumnInfo(name = "first_name") val firstName:String,
    @ColumnInfo(name = "last_name") val lastName:String
)

