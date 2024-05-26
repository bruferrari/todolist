package com.ferrarib.todolist.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ferrarib.todolist.domain.Task

@Dao
interface TasksDao {
    @Query("SELECT * FROM tasks")
    fun getAllTasks(): List<Task>

    @Query("SELECT * FROM tasks WHERE id = :id")
    fun getById(id: Int): Task

    @Insert
    fun insertTask(task: Task)

    @Delete
    fun removeTask(task: Task)
}