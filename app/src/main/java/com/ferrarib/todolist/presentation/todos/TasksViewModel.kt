package com.ferrarib.todolist.presentation.todos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferrarib.todolist.core.di.IODispatcher
import com.ferrarib.todolist.domain.Task
import com.ferrarib.todolist.domain.usecase.DeleteTask
import com.ferrarib.todolist.domain.usecase.GetTasks
import com.ferrarib.todolist.domain.usecase.GetUniqueTask
import com.ferrarib.todolist.domain.usecase.UpdateTaskCompletion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val getTasks: GetTasks,
    private val deleteTask: DeleteTask,
    private val getUniqueTask: GetUniqueTask,
    private val updateTaskCompletion: UpdateTaskCompletion,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _tasksListState: MutableStateFlow<List<Task>> = MutableStateFlow(emptyList())
    val tasksListState: StateFlow<List<Task>> = _tasksListState

    init {
        viewModelScope.launch(dispatcher) {
            getTasks().collectLatest { tasks ->
                _tasksListState.update { tasks }
            }
        }
    }

    fun removeTask(id: Long) {
        viewModelScope.launch(dispatcher) {
            val task = getUniqueTask(id).first()
            deleteTask(task)
        }
    }

    fun setTaskCompletion(id: Long, value: Boolean) {
        viewModelScope.launch(dispatcher) {
            updateTaskCompletion(id, value)
        }
    }
}