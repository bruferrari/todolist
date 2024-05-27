package com.ferrarib.todolist.domain.usecase

import com.ferrarib.todolist.core.exception.InvalidIdentifierException
import com.ferrarib.todolist.data.local.repository.TasksRepository
import com.ferrarib.todolist.taskEntityFlow
import io.mockk.Called
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetUniqueTaskTest {
    private lateinit var getUniqueTask: GetUniqueTask
    private lateinit var repository: TasksRepository

    @Before
    fun setUp() {
        repository = mockk()
        getUniqueTask = GetUniqueTask(repository)
    }

    @Test
    fun `ensure that repository is being called for a single task`() {
        every { repository.getById(any()) } returns taskEntityFlow

        runTest {
            getUniqueTask(1L)
            verify(exactly = 1) { repository.getById(any()) }
            verify(exactly = 1) { repository.getById(any()) }
        }
    }

    @Test(expected = InvalidIdentifierException::class)
    fun `ensure that repository don't get anything when id is not valid`() {
        every { repository.getById(any()) } returns taskEntityFlow

        runTest {
            getUniqueTask(-1L)
            verify { repository.getById(any()) wasNot Called }
        }
    }
}