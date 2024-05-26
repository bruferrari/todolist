package com.ferrarib.todolist.presentation.todos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferrarib.todolist.data.local.repository.TasksRepository
import com.ferrarib.todolist.domain.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val repository: TasksRepository
) : ViewModel() {

    private val _tasksListState: MutableStateFlow<List<Task>> = MutableStateFlow(emptyList())
    val tasksListState: StateFlow<List<Task>> = _tasksListState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            // temp
            for (index in 1..5) {
                repository.insert(
                    Task(
                        content = "this is a test using room db $index",
                        dateTime = "",
                        isComplete = false
                    )
                )
            }

            val allTasks = repository.getAll()
            _tasksListState.update { allTasks }
        }

    }
}