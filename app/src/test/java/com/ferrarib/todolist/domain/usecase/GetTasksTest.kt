package com.ferrarib.todolist.domain.usecase

import com.ferrarib.todolist.data.local.repository.TasksRepository
import com.ferrarib.todolist.taskEntityListFlow
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetTasksTest {
    private lateinit var getTasks: GetTasks
    private lateinit var repository: TasksRepository

    @Before
    fun setUp() {
        repository = mockk<TasksRepository>()

        getTasks = GetTasks(repository)
    }

    @Test
    fun `ensure get tasks use case is returning the correct value`() {
        every { repository.getAll() } returns taskEntityListFlow

        runTest {
            val tasks = getTasks().first()
            assertEquals(3, tasks.size)
        }
    }

    @Test
    fun `ensure get tasks use case returns empty`() {
        every { repository.getAll() } returns flowOf(emptyList())

        runTest {
            val tasks = getTasks().first()
            assertEquals(0, tasks.size)
        }
    }
}