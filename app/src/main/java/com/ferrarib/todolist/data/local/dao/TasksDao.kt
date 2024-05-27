package com.ferrarib.todolist.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ferrarib.todolist.data.local.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {
    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE id = :id")
    fun getById(id: Long): Flow<TaskEntity>

    @Insert
    fun insertTask(task: TaskEntity)

    @Delete
    fun removeTask(task: TaskEntity)

    @Query("UPDATE tasks SET isComplete = :isComplete WHERE id = :id")
    fun updateTaskCompletion(id: Long, isComplete: Boolean)
}