package com.example.taskpomodore.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskpomodore.model.Todo

@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class TodoDatabase: RoomDatabase(){
    abstract fun taskDao(): TodoDatabaseDao
}