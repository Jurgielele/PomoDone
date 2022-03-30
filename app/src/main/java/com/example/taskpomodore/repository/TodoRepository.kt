package com.example.taskpomodore.repository

import com.example.taskpomodore.data.TodoDatabaseDao
import com.example.taskpomodore.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TodoRepository @Inject constructor(private val todoDatabaseDao: TodoDatabaseDao) {
    suspend fun addTodo(todo: Todo) = todoDatabaseDao.insert(todo)
    suspend fun updateTodo(todo: Todo) = todoDatabaseDao.update(todo)
    suspend fun deleteTodo(todo: Todo) = todoDatabaseDao.deleteTodo(todo)

    fun getAllTodos(): Flow<List<Todo>>{
        return todoDatabaseDao.getAllTodos().flowOn(Dispatchers.IO).conflate()
    }
}