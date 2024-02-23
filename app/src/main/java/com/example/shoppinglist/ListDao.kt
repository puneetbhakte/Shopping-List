package com.example.shoppinglist

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ListDao {

    @Upsert
    suspend fun upsertData(list:List)
    @Delete
    suspend fun deleteData(list: List)

    @Query("SELECT * FROM LIST ORDER BY id DESC")
    fun getAllData(): LiveData<kotlin.collections.List<List>>
}