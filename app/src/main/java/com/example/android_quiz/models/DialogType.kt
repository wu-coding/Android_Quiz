package com.example.android_quiz.models

enum class DialogType(val title: String, val msg: String) {
    ABOUT("About", aboutMsg), QUIZ("Quiz Help", helpQuizMsg), RECORDS(
        "Records Help",
        helpRecordsMsg
    )
}

val aboutMsg = "This is a Quiz Project created by Alex Wu, as part of portfolio. \n" +
        "More examples can be found at wu-coding github."

val helpQuizMsg = "Yellow indicates user did not select correct answer. \n" +
        "Score is calculated by wins - loss."

val helpRecordsMsg = "Blue records indicated current record. \n" +
        "Green record indicates all user records."