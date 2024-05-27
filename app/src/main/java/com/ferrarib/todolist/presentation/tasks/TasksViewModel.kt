package com.ferrarib.todolist.presentation.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferrarib.todolist.core.di.IODispatcher
import com.ferrarib.todolist.core.utils.corroutineExceptionHandler
import com.ferrarib.todolist.domain.model.Task
import com.ferrarib.todolist.domain.usecase.DeleteTask
import com.ferrarib.todolist.domain.usecase.GetTasks
import com.ferrarib.todolist.domain.usecase.GetUniqueTask
import com.ferrarib.todolist.domain.usecase.NotValidIdentifierException
import com.ferrarib.todolist.domain.usecase.UpdateTaskCompletion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val getTasks: GetTasks,
    private val deleteTask: DeleteTask,
    private val getUniqueTask: GetUniqueTask,
    private val updateTaskCompletion: UpdateTaskCompletion,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val coroutineContext = dispatcher + corroutineExceptionHandler

    private val _tasksListState: MutableStateFlow<List<Task>> = MutableStateFlow(emptyList())
    val tasksListState: StateFlow<List<Task>> = _tasksListState

    init {
        viewModelScope.launch(coroutineContext) {
            getTasks().collectLatest { tasks ->
                _tasksListState.update { tasks }
            }
        }
    }

    fun removeTask(id: Long) {
        viewModelScope.launch(coroutineContext) {
            val task = getUniqueTask(id).first()
            deleteTask(task)
        }
    }

    fun setTaskCompletion(id: Long, value: Boolean) {
        try {
            viewModelScope.launch(coroutineContext) {
                updateTaskCompletion(id, value)
            }
        } catch (e: NotValidIdentifierException) {
            Timber.e(e)
        }
    }
}