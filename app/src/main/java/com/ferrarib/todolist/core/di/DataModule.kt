package com.ferrarib.todolist.core.di

import android.content.Context
import androidx.room.Room
import com.ferrarib.todolist.data.local.db.TasksDatabase
import com.ferrarib.todolist.data.local.repository.TasksRepository
import com.ferrarib.todolist.data.local.repository.TasksRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): TasksDatabase =
        Room.databaseBuilder(
            context,
            TasksDatabase::class.java,
            TASKS_DB_NAME
        ).build()

    @Singleton
    @Provides
    fun providesTaskRepositoryImpl(db: TasksDatabase): TasksRepository = TasksRepositoryImpl(db)

    private const val TASKS_DB_NAME = "tasks-db"
}