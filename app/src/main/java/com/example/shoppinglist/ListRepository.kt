package com.example.shoppinglist

class ListRepository(private val db:ListDatabase) {

    suspend fun upsertdata(list: List) = db.DataAccessObject().upsertData(list)
    suspend fun deletetdata(list: List) = db.DataAccessObject().deleteData(list)

    fun getdata() = db.DataAccessObject().getAllData()

}