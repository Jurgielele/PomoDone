package com.example.taskpomodore.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "on_boarding_pref")

class DataStoreRepository(context: Context){
    private object PreferencesKey{
        val onBoardingKey = booleanPreferencesKey(name = "on_boarding_completed")
        val nameKey = stringPreferencesKey(name = "name")
    }

    private val dataStore = context.dataStore

    suspend fun saveOnBoardingState(completed: Boolean){
        dataStore.edit{preferences -> preferences[PreferencesKey.onBoardingKey] = completed}
    }
    suspend fun saveName(name: String){
        dataStore.edit{preferences -> preferences[PreferencesKey.nameKey] = name}
    }

    fun readOnBoardingState(): Flow<Boolean>{
        return dataStore.data
            .catch { exception ->
                if(exception is IOException){
                    emit(emptyPreferences())
                }else{
                    throw exception
                }
            }.map { it ->
                val onBoardingState = it[PreferencesKey.onBoardingKey] ?: false
                onBoardingState
            }
    }

    fun readUserName(): Flow<String>{
        return dataStore.data
            .catch { exception ->
                if(exception is IOException){
                    emit(emptyPreferences())
                }else{
                    throw exception
                }
            }.map {
                val name = it[PreferencesKey.nameKey] ?: ""
                name
            }
    }
}