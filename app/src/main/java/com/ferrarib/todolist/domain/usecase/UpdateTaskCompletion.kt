package com.ferrarib.todolist.domain.usecase

import com.ferrarib.todolist.core.exception.InvalidIdentifierException
import com.ferrarib.todolist.data.local.repository.TasksRepository
import javax.inject.Inject

class UpdateTaskCompletion @Inject constructor(
    private val repository: TasksRepository
) {
    operator fun invoke(id: Long, value: Boolean) {
        if (id < 0L) throw InvalidIdentifierException(id = id)

        repository.updateTaskCompletion(id = id, value = value)
    }
}