package com.example.taskpomodore.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskpomodore.model.Todo
import com.example.taskpomodore.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodosViewModel @Inject constructor(private val todoRepository: TodoRepository):  ViewModel() {

    private val _todoList = MutableStateFlow<List<Todo>>(emptyList())
    val todoList = _todoList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO){
            todoRepository.getAllTodos().distinctUntilChanged()
                .collect { todoList ->
                    if(todoList.isNullOrEmpty()){
                        Log.d("TodosViewModel", "EMPTY LIST")
                    }else{
                        _todoList.value = todoList
                    }
                }
        }
    }
    //save update delete get all, init list of todos /2 state variables 1 to load to it second to observe

    fun addTodo(todo: Todo) = viewModelScope.launch { todoRepository.addTodo(todo) }
    fun deleteTodo(todo: Todo) = viewModelScope.launch { todoRepository.deleteTodo(todo) }
    fun updateTodo(todo: Todo) = viewModelScope.launch { todoRepository.updateTodo(todo) }



}