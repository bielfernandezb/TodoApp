package com.bielfernandezb.todoapp.data.repository.local

import com.bielfernandezb.todoapp.data.repository.local.db.TaskDao
import com.bielfernandezb.todoapp.domain.entity.Task
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val taskDao: TaskDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getAllTasks() = withContext(ioDispatcher) { return@withContext taskDao.getTasks() }

    suspend fun insertAll(task: Task) = withContext(ioDispatcher) {
        taskDao.insertTask(task)
    }
}