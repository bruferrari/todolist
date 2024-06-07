package com.ferrarib.todolist.presentation.tasks

import com.ferrarib.todolist.domain.model.Task
import com.ferrarib.todolist.domain.usecase.DeleteTask
import com.ferrarib.todolist.domain.usecase.GetTasks
import com.ferrarib.todolist.domain.usecase.GetUniqueTask
import com.ferrarib.todolist.domain.usecase.UpdateTaskCompletion
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class TasksViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    @Test
    fun `ensure that list of tasks state is filled`() {
        val mockedList = listOf(
            Task(id = 1L, content = "task 1", isComplete = false),
            Task(id = 2L, content = "task 2", isComplete = false),
            Task(id = 3L, content = "task 3", isComplete = false)
        )

        val getTasks = mockk<GetTasks>()

        // need to answer for viewmodel init
        every { getTasks.invoke() } returns flowOf(mockedList)

        val viewModel = TasksViewModel(
            getTasks = getTasks,
            getUniqueTask = mockk(),
            deleteTask = mockk(),
            updateTaskCompletion = mockk(),
            dispatcher = testDispatcher
        )

        runTest {
            val current = viewModel.tasksListState.first()
            assertEquals(3, current.size)
        }
    }

    @Test
    fun `ensure that tasks are being removed`() {
        val getUniqueTask = mockk<GetUniqueTask>()
        val deleteTask = mockk<DeleteTask>()

        val viewModel = TasksViewModel(
            getTasks = mockk(),
            getUniqueTask = getUniqueTask,
            deleteTask = deleteTask,
            updateTaskCompletion = mockk(),
            dispatcher = testDispatcher
        )

        val task = Task(
            id = 3L,
            content = "",
            isComplete = false
        )
        every { getUniqueTask(any()) } returns flowOf(task)

        viewModel.removeTask(3L)
        verify(exactly = 1) { getUniqueTask(3L) }
        verify(exactly = 1) { deleteTask(task) }
    }

    @Test
    fun `ensure tasks are being marked as complete`() {
        val updateTaskCompletion = mockk<UpdateTaskCompletion>()
        val viewModel = TasksViewModel(
            getTasks = mockk(),
            getUniqueTask = mockk(),
            deleteTask = mockk(),
            updateTaskCompletion = updateTaskCompletion,
            dispatcher = testDispatcher
        )

        every { updateTaskCompletion(any(), any()) } returns Unit
        viewModel.setTaskCompletion(3L, value = true)

        verify(exactly = 1) { updateTaskCompletion(any(), any()) }
    }
}