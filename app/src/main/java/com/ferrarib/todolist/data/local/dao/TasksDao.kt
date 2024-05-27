package com.ferrarib.todolist.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ferrarib.todolist.domain.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {
    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE id = :id")
    fun getById(id: Long): Flow<Task>

    @Insert
    fun insertTask(task: Task)

    @Delete
    fun removeTask(task: Task)

    @Query("UPDATE tasks SET isComplete = :isComplete WHERE id = :id")
    fun updateTaskCompletion(id: Long, isComplete: Boolean)
}