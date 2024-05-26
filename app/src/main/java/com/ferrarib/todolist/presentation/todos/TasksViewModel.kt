package com.ferrarib.todolist.presentation.todos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferrarib.todolist.core.di.IODispatcher
import com.ferrarib.todolist.data.local.repository.TasksRepository
import com.ferrarib.todolist.domain.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val repository: TasksRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _tasksListState: MutableStateFlow<List<Task>> = MutableStateFlow(emptyList())
    val tasksListState: StateFlow<List<Task>> = _tasksListState

    init {
        viewModelScope.launch(dispatcher) {
            val allTasks = repository.getAll()
            _tasksListState.update { allTasks }
        }
    }

    fun removeTask(id: Long) {
        viewModelScope.launch(dispatcher) {
            val task = repository.getById(id)
            repository.remove(task)

            _tasksListState.update { repository.getAll() }
        }
    }
}