package com.example.taskpomodore.data

import androidx.room.*
import com.example.taskpomodore.model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDatabaseDao{
    @Query("SELECT * from todo_table")
    fun getAllTodos():Flow<List<Todo>>

    @Query("SELECT * from todo_table where id =:id")
    suspend fun getNoteById(id: String): Todo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Todo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)
}