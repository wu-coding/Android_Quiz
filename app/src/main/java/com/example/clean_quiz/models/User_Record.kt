package com.example.clean_quiz.models

import androidx.room.Embedded
import androidx.room.Relation
import com.example.clean_quiz.data.User

data class User_Record(
    @Embedded val user: User,
    @Relation(
        parentColumn = "user.userId",
        entityColumn = "userID"
    )val record: List<Record>)

