package com.example.clean_quiz.DAO

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.clean_quiz.models.FullRecord
import com.example.clean_quiz.models.Record
import com.example.clean_quiz.models.RecordPreferences
import com.example.clean_quiz.models.RecordScore

@Dao
interface RecordDao {
    @Query("Select * From record Where record_id = :record_ID ")
    fun loadApiParams(record_ID: Int): Record


    @Insert(entity = Record::class)
    fun updateRecordPreferences(recordPreferences: RecordPreferences):Long

    @Update(entity = Record::class)
    fun updateRecordScore(recordScore: RecordScore)

    @Query(
        "SELECT  * FROM record " +
                "INNER JOIN user ON record.user_id = user.user_id " +
                "Order By total_score Desc Limit 10"
    )
    fun getTop10(): Array<FullRecord>

    @Query(
        "SELECT  * FROM record " +
                "INNER JOIN user ON record.user_id = user.user_id " +
                "Where record.user_id = :userID " +
                "Order By total_score Desc"
    )
    fun getCurrentRecords(userID: Int): Array<FullRecord>

    @Query(
        "SELECT  * FROM record " +
                "INNER JOIN user ON record.user_id = user.user_id " +
                "Where user.first_name = :firstName AND user.last_name =:lastName  " +
                "Order By total_score DESC"
    )
    fun getUserRecords(firstName: String, lastName: String): Array<FullRecord>


    @Query(
        "SELECT  * FROM record " +
                "INNER JOIN user ON record.user_id = user.user_id " +
                "Where record.user_id = :categoryID " +
                "Order By total_score "
    )
    fun getCategoryRecords(categoryID:String): Array<FullRecord>

    @RawQuery
    fun searchRecordsQuery(query: SupportSQLiteQuery):Array<FullRecord>

}

