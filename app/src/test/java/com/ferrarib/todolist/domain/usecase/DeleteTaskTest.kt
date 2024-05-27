package com.ferrarib.todolist.domain.usecase

import com.ferrarib.todolist.data.local.repository.TasksRepository
import com.ferrarib.todolist.domain.mappers.toDomain
import com.ferrarib.todolist.domain.mappers.toEntity
import com.ferrarib.todolist.taskEntityListFlow
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class DeleteTaskTest {
    private lateinit var deleteTask: DeleteTask
    private lateinit var repository: TasksRepository

    @Before
    fun setUp() {
        repository = mockk()
        deleteTask = DeleteTask(repository)
    }

    @Test
    fun `ensure delete task use case is removing the actual task`() {
        every { repository.remove(any()) } returns Unit

        runTest {
            val task = taskEntityListFlow.first()[0].toDomain()
            deleteTask(task)

            verify(exactly = 1) { repository.remove(task.toEntity()) }
        }
    }
}