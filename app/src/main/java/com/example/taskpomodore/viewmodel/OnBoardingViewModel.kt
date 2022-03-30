package com.example.taskpomodore.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskpomodore.repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val repository: DataStoreRepository): ViewModel(){

    var name = mutableStateOf("")

    fun saveOnBoardingState(completed: Boolean){
        viewModelScope.launch(Dispatchers.IO){
            repository.saveOnBoardingState(completed = completed)
        }
    }
    fun saveUsername(name: String){
        viewModelScope.launch(Dispatchers.IO){
            repository.saveName(name)
        }
    }
}