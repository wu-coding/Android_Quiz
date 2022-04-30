package com.example.clean_quiz.data.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.clean_quiz.data.models.Record

@Dao
interface RecordDao {
    @Query("Select * From record Where recordId = :record_ID ")
    fun loadApiParams(record_ID:Int): Record

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecord(vararg record: Record)

}