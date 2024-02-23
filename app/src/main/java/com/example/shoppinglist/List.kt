package com.example.shoppinglist

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "List")
data class List(
    var name:String,
    var amount:Int,
    @PrimaryKey(autoGenerate = true)
    var id:Int
)
