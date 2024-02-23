package com.example.shoppinglist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [List::class], version = 1)
abstract class ListDatabase:RoomDatabase() {
    abstract fun DataAccessObject():ListDao

    companion object{
        @Volatile
        private var instance:ListDatabase? = null
        private val LOCK = Any()


        operator fun invoke(context:Context) = instance ?:
        synchronized(LOCK){
        instance ?:
            createdatabase(context).also {
                instance = it
            }
        }

        private fun createdatabase(context: Context) =
            Room.
            databaseBuilder(context.applicationContext,ListDatabase::class.java,"List").build()
    }
}