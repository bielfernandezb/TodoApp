package com.bielfernandezb.todoapp.model.repository.local

import com.bielfernandezb.todoapp.model.entities.Task
import com.bielfernandezb.todoapp.model.repository.local.db.TaskDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val taskDao: TaskDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getAllTasks() = withContext(ioDispatcher) { return@withContext taskDao.getTasks() }

    suspend fun getTaskById(id: Long) =
        withContext(ioDispatcher) { return@withContext taskDao.getTaskById(id) }

    suspend fun updateTask(task: Task) = withContext(ioDispatcher) { taskDao.updateTask(task) }

    suspend fun insertAll(task: Task) = withContext(ioDispatcher) {
        taskDao.insertTask(task)
    }
}