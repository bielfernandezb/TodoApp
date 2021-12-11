package com.bielfernandezb.todoapp.domain.use_case

import com.bielfernandezb.todoapp.core.Resource
import com.bielfernandezb.todoapp.domain.entity.Task
import com.bielfernandezb.todoapp.domain.repository.Repository
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val repository: Repository
) : BaseUseCase<Unit, Resource<List<Task?>>> {

    override suspend fun invoke(unit: Unit): Resource<List<Task?>> =
        repository.getTasks()

}