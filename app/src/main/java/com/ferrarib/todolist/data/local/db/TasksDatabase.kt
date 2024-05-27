package com.ferrarib.todolist.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ferrarib.todolist.data.local.dao.TasksDao
import com.ferrarib.todolist.data.local.entity.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class TasksDatabase : RoomDatabase() {
    abstract fun taskDao() : TasksDao
}