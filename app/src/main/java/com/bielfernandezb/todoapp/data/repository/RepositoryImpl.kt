package com.bielfernandezb.todoapp.data.repository

import com.bielfernandezb.todoapp.core.Resource
import com.bielfernandezb.todoapp.data.repository.local.LocalDataSource
import com.bielfernandezb.todoapp.domain.entity.Task
import com.bielfernandezb.todoapp.domain.repository.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : Repository {

    override suspend fun getTasks(): Resource<List<Task?>> {
        return withContext(ioDispatcher) {
            localDataSource.getAllTasks().let { return@withContext Resource.success(it) }
        }
    }

    override suspend fun insertTask(task: Task) {
        return withContext(ioDispatcher) {
            localDataSource.insertAll(task)
        }
    }

}