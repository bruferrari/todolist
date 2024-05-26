package com.ferrarib.todolist.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ferrarib.todolist.data.local.DateTimeTypeConverter
import com.ferrarib.todolist.data.local.dao.TasksDao
import com.ferrarib.todolist.domain.Task

@Database(entities = [Task::class], version = 1)
@TypeConverters(DateTimeTypeConverter::class)
abstract class TasksDatabase : RoomDatabase() {
    abstract fun taskDao() : TasksDao
}