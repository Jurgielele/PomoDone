package com.example.taskpomodore.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.w3c.dom.Text
import java.time.Instant
import java.util.*

@Entity(tableName = "todo_table")
data class Todo(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    @ColumnInfo (name="todo_name")val task: String,
    @ColumnInfo (name = "todo_completed") val completed: Boolean = false,
    @ColumnInfo(name = "todo_entry_date") val entryDate: Date = Date.from(Instant.now())
)