package com.ferrarib.todolist.domain.usecase

import com.ferrarib.todolist.data.local.repository.TasksRepository
import com.ferrarib.todolist.domain.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTasks @Inject constructor(
    private val repository: TasksRepository
) {
    operator fun invoke(): Flow<List<Task>> {
        return repository.getAll()
    }
}