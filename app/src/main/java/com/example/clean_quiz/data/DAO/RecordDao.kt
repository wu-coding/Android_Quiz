package com.example.clean_quiz.data.DAO

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface RecordDao {
    @Query("Select * From record Where recordId = :record_ID ")
    fun loadApiParams(record_ID:Int): Record

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecord(vararg record:Record)

}