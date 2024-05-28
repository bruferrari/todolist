package com.ferrarib.todolist.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferrarib.todolist.core.di.IODispatcher
import com.ferrarib.todolist.core.exception.InvalidIdentifierException
import com.ferrarib.todolist.core.utils.corroutineExceptionHandler
import com.ferrarib.todolist.domain.model.Task
import com.ferrarib.todolist.domain.usecase.AddTask
import com.ferrarib.todolist.domain.usecase.GetUniqueTask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getUniqueTask: GetUniqueTask,
    private val addTask: AddTask,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val coroutineContext = dispatcher + corroutineExceptionHandler

    private val _selectedTaskState = MutableStateFlow<Task?>(null)
    val selectedTaskState: StateFlow<Task?> = _selectedTaskState

    fun getTask(id: Long) {
        viewModelScope.launch(coroutineContext) {
            getUniqueTask(id).collectLatest { task ->
                try {
                    _selectedTaskState.update { task }
                } catch (e: InvalidIdentifierException) {
                    Timber.e(e)
                }
            }
        }
    }

    fun addNewTask(content: String) {
        viewModelScope.launch(coroutineContext) {
            addTask(task = Task(content = content, isComplete = false))
        }
    }
}