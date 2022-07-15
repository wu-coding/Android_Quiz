package com.example.android_quiz.models

import androidx.room.Embedded
import androidx.room.Relation
import com.example.android_quiz.data.User

data class User_Record(
    @Embedded val user: User,
    @Relation(
        parentColumn = "user.userId",
        entityColumn = "userID"
    )val record: List<Record>)

