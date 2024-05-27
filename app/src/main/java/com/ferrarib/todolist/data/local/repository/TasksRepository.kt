package com.ferrarib.todolist.data.local.repository

import com.ferrarib.todolist.data.local.dao.TasksDao
import com.ferrarib.todolist.data.local.db.TasksDatabase
import com.ferrarib.todolist.data.local.entity.TaskEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface TasksRepository {

    fun getAll(): Flow<List<TaskEntity>>

    fun getById(id: Long): Flow<TaskEntity>

    fun insert(task: TaskEntity)

    fun remove(task: TaskEntity)

    fun updateTaskCompletion(id: Long, value: Boolean)
}

class TasksRepositoryImpl @Inject constructor(
    db: TasksDatabase
) : TasksRepository {
    private val dao: TasksDao = db.taskDao()

    override fun insert(task: TaskEntity) {
        dao.insertTask(task)
    }

    override fun remove(task: TaskEntity) {
        dao.removeTask(task)
    }

    override fun updateTaskCompletion(id: Long, value: Boolean) {
        dao.updateTaskCompletion(id, value)
    }

    override fun getAll(): Flow<List<TaskEntity>> {
         return dao.getAllTasks()
    }

    override fun getById(id: Long): Flow<TaskEntity> {
        return dao.getById(id)
    }
}