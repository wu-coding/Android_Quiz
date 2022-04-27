package com.example.clean_quiz.data.DAO

import androidx.room.Embedded
import androidx.room.Relation

data class User_Record(
    @Embedded val user:User,
    @Relation(
        parentColumn = "user.userId",
        entityColumn = "userID"
    )val record: List<Record>)

