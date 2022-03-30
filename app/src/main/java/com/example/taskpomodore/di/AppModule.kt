package com.example.taskpomodore.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taskpomodore.data.TodoDatabase
import com.example.taskpomodore.data.TodoDatabaseDao
import com.example.taskpomodore.repository.DataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule{

    @Singleton
    @Provides
    fun provideDataStoreRepository(@ApplicationContext context: Context) = DataStoreRepository(context)

    @Singleton
    @Provides
    fun provideTodoDao(todoDatabase: TodoDatabase): TodoDatabaseDao = todoDatabase.taskDao()

    @Singleton
    @Provides
    fun provideTodoDatabase(@ApplicationContext context: Context): TodoDatabase {
        return Room.databaseBuilder(
            context,
            TodoDatabase::class.java,
            "todo_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

}