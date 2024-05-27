package com.ferrarib.todolist.domain.mappers

import com.ferrarib.todolist.data.local.entity.TaskEntity
import com.ferrarib.todolist.domain.model.Task

fun TaskEntity.toDomain() = Task(
    id = id,
    content = content,
    isComplete = isComplete
)

fun Task.toEntity() = TaskEntity(
    id = id,
    content = content,
    isComplete = isComplete
)

fun List<TaskEntity>.toDomainList() = map { it.toDomain() }

fun List<Task>.toEntityList() = map { it.toEntity() }