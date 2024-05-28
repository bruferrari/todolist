package com.ferrarib.todolist.presentation.details

import com.ferrarib.todolist.domain.model.Task
import com.ferrarib.todolist.domain.usecase.AddTask
import com.ferrarib.todolist.domain.usecase.GetUniqueTask
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class DetailsViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var viewModel: DetailsViewModel

    private lateinit var getUniqueTask: GetUniqueTask
    private lateinit var addTask: AddTask

    @Before
    fun setUp() {
        addTask = mockk<AddTask>()
        getUniqueTask = mockk<GetUniqueTask>()

        viewModel = DetailsViewModel(
            getUniqueTask = getUniqueTask,
            addTask = addTask,
            dispatcher = testDispatcher
        )
    }

    @Test
    fun `ensure that task flow is filled with task`() {
        every { getUniqueTask(any()) } returns flowOf(
            Task(
                id = 1L,
                content = "task 1",
                isComplete = false
            )
        )

        viewModel.getTask(1L)

        runTest {
            val current = viewModel.selectedTaskState.first()
            assertNotNull(current)
        }
    }

    @Test
    fun `ensure adding a new task`() {
        val task = Task(
            id = null,
            content = "test",
            isComplete = false
        )

        every { addTask(any()) } returns Unit

        viewModel.addNewTask("test")
        verify(exactly = 1) { addTask(task) }
    }
}