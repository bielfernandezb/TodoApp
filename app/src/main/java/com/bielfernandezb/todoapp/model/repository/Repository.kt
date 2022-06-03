package com.bielfernandezb.todoapp.model.repository

import com.bielfernandezb.todoapp.model.entities.Task
import com.bielfernandezb.todoapp.model.repository.local.LocalDataSource
import com.bielfernandezb.todoapp.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getTasks(): Resource<List<Task?>> {
        return withContext(ioDispatcher) {
            localDataSource.getAllTasks().let { return@withContext Resource.success(it) }
        }
    }

    suspend fun insertTask(task: Task) {
        return withContext(ioDispatcher) {
            localDataSource.insertAll(task)
        }
    }

}