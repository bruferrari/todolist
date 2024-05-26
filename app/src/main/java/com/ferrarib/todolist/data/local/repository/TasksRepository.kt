package com.ferrarib.todolist.data.local.repository

import com.ferrarib.todolist.data.local.dao.TasksDao
import com.ferrarib.todolist.data.local.db.TasksDatabase
import com.ferrarib.todolist.domain.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface TasksRepository {

    fun getAll(): Flow<List<Task>>

    fun getById(id: Long): Flow<Task>

    fun insert(task: Task)

    fun remove(task: Task)
}

class TasksRepositoryImpl @Inject constructor(
    db: TasksDatabase
) : TasksRepository {
    private val dao: TasksDao = db.taskDao()

    override fun insert(task: Task) {
        dao.insertTask(task)
    }

    override fun remove(task: Task) {
        dao.removeTask(task)
    }

    override fun getAll(): Flow<List<Task>> {
         return dao.getAllTasks()
    }

    override fun getById(id: Long): Flow<Task> {
        return dao.getById(id)
    }
}