package com.ferrarib.todolist.presentation.todos

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor() : ViewModel() {
    private val mockList: List<String>
        get() {
            val temp = mutableListOf<String>()
            (0..20).forEach { index ->
                temp.add("This is a test $index")
            }

            return temp.toList()
        }

    private val _tasksListState: MutableStateFlow<List<String>> = MutableStateFlow(mockList)
    val tasksListState: StateFlow<List<String>> = _tasksListState
}