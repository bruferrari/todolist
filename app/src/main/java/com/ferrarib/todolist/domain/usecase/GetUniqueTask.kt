package com.ferrarib.todolist.domain.usecase

import com.ferrarib.todolist.core.exception.InvalidIdentifierException
import com.ferrarib.todolist.data.local.repository.TasksRepository
import com.ferrarib.todolist.domain.mappers.toDomain
import com.ferrarib.todolist.domain.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUniqueTask @Inject constructor(
    private val repository: TasksRepository
) {
    operator fun invoke(id: Long): Flow<Task> {
        if (id < 0L) throw InvalidIdentifierException(id)
        return repository.getById(id).map { it.toDomain() }
    }
}
