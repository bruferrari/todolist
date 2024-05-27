package com.ferrarib.todolist.core.exception

class InvalidIdentifierException(private val id: Long) : RuntimeException() {
    override val message: String
        get() = "Id $id is not valid for retrieving data"
}