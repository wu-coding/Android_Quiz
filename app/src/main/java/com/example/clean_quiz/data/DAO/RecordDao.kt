package com.example.clean_quiz.data.DAO

import androidx.room.*
import com.example.clean_quiz.data.models.Record
import com.example.clean_quiz.data.models.RecordPreferences
import com.example.clean_quiz.data.models.RecordScore

@Dao
interface RecordDao {
    @Query("Select * From record Where recordId = :record_ID ")
    fun loadApiParams(record_ID:Int): Record

/*    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecord(record: Record):Long*/

    @Insert(entity = RecordPreferences::class)
    fun updateRecordPreferences(recordPreferences:RecordPreferences)

    @Update(entity = RecordScore::class)
    fun updateRecordScore(recordScore:RecordScore)
}

