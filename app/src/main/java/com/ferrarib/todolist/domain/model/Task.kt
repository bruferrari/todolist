package com.ferrarib.todolist.domain.model

data class Task(
    val id: Long? = null,
    val content: String,
    val isComplete: Boolean
)
