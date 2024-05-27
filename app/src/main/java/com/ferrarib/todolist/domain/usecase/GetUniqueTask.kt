package com.ferrarib.todolist.domain.usecase

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
        if (id < 0) throw NotValidIdentifierException(id)
        return repository.getById(id).map { it.toDomain() }
    }
}

class NotValidIdentifierException(private val id: Long) : RuntimeException() {
    override val message: String
        get() = "Id $id is not valid for retrieving data"
}