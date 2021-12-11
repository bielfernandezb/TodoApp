package com.bielfernandezb.todoapp.domain.use_case

import com.bielfernandezb.todoapp.domain.entity.Task
import com.bielfernandezb.todoapp.domain.repository.Repository
import javax.inject.Inject

class InsertTaskUseCase @Inject constructor(
    private val repository: Repository
) : BaseUseCase<Task, Unit> {

    override suspend fun invoke(params: Task) =
        repository.insertTask(params)
}