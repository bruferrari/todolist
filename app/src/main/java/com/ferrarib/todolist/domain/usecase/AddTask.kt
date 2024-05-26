package com.ferrarib.todolist.domain.usecase

import com.ferrarib.todolist.data.local.repository.TasksRepository
import com.ferrarib.todolist.domain.Task
import javax.inject.Inject

class AddTask @Inject constructor(
    private val repository: TasksRepository
) {
    operator fun invoke(content: String) {
        val task = Task(content = content, isComplete = false)
        repository.insert(task)
    }
}