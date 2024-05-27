package com.ferrarib.todolist.core.utils

import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber

val corroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
    Timber.e(throwable)
}