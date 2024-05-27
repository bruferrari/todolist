package com.ferrarib.todolist.domain.usecase

import com.ferrarib.todolist.data.local.repository.TasksRepository
import com.ferrarib.todolist.domain.mappers.toEntity
import com.ferrarib.todolist.domain.model.Task
import javax.inject.Inject

class DeleteTask @Inject constructor(
    private val repository: TasksRepository
) {
    operator fun invoke(task: Task) {
        repository.remove(task.toEntity())
    }
}