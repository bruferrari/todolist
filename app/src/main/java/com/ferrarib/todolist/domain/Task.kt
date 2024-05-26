package com.ferrarib.todolist.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey val id: Long? = null,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP") val createdAt: String? = null,
    @ColumnInfo val content: String,
    @ColumnInfo val isComplete: Boolean
)
