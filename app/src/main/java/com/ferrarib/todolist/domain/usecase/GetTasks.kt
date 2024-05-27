package com.ferrarib.todolist.domain.usecase

import com.ferrarib.todolist.data.local.repository.TasksRepository
import com.ferrarib.todolist.domain.mappers.toDomainList
import com.ferrarib.todolist.domain.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTasks @Inject constructor(
    private val repository: TasksRepository
) {
    operator fun invoke(): Flow<List<Task>> {
        return repository.getAll().map { it.toDomainList() }
    }
}