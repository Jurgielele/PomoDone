package com.example.taskpomodore.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.taskpomodore.model.Todo
import com.example.taskpomodore.utils.DateConverter
import com.example.taskpomodore.utils.UUIDConverter

@Database(entities = [Todo::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class TodoDatabase: RoomDatabase(){
    abstract fun taskDao(): TodoDatabaseDao
}