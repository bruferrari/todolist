package com.ferrarib.todolist.domain.usecase

import com.ferrarib.todolist.data.local.repository.TasksRepository
import com.ferrarib.todolist.taskEntityFlow
import io.mockk.Called
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class UpdateTaskCompletionTest {
    private lateinit var updateTaskCompletion: UpdateTaskCompletion
    private lateinit var repository: TasksRepository

    @Before
    fun setUp() {
        repository = mockk()
        updateTaskCompletion = UpdateTaskCompletion(repository)
    }

    @Test
    fun `ensure right function is being called to update the task`() {
        every { repository.updateTaskCompletion(any(), any()) } returns Unit

        runTest {
            updateTaskCompletion(1L, true)
            verify(exactly = 1) { repository.updateTaskCompletion(any(), any()) }
        }
    }

    @Test(expected = NotValidIdentifierException::class)
    fun `ensure that repository don't update anything when id is not valid`() {
        every { repository.updateTaskCompletion(any(), any()) } returns Unit

        runTest {
            updateTaskCompletion(-1L, true)
            verify { repository.updateTaskCompletion(any(), any()) wasNot Called }
        }
    }
}