package com.bielfernandezb.todoapp.domain.repository

import com.bielfernandezb.todoapp.core.Resource
import com.bielfernandezb.todoapp.domain.entity.Task

interface Repository {

    suspend fun getTasks(): Resource<List<Task?>>

    suspend fun insertTask(task: Task)

}