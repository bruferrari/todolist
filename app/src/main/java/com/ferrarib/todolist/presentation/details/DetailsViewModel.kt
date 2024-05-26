package com.ferrarib.todolist.presentation.details

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
class DetailsViewModel @Inject constructor(
    private val repository: TasksRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _selectedTaskState = MutableStateFlow<Task?>(null)
    val selectedTaskState: StateFlow<Task?> = _selectedTaskState

    fun getTask(id: Long) {
        viewModelScope.launch(dispatcher) {
            val task = repository.getById(id)
            _selectedTaskState.update { task }
        }
    }

    fun addTask(content: String) {
        viewModelScope.launch(dispatcher) {
            repository.insert(Task(content = content, isComplete = false))
        }
    }
}