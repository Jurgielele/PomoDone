package com.example.taskpomodore.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.w3c.dom.Text
import java.util.*

@Entity(tableName = "todo_table")
data class Todo(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    @ColumnInfo (name="task_name")val task: String,
    @ColumnInfo (name = "completed") val completed: Boolean = false
)