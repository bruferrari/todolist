package com.ferrarib.todolist

import com.ferrarib.todolist.data.local.entity.TaskEntity
import kotlinx.coroutines.flow.flowOf

internal val taskList = listOf(
    TaskEntity(id = 1L, content = "task 1", isComplete = false),
    TaskEntity(id = 2L, content = "task 2", isComplete = false),
    TaskEntity(id = 3L, content = "task 3", isComplete = false)
)

internal val taskEntityListFlow = flowOf(taskList)
internal val taskEntityFlow = flowOf(taskList[0])