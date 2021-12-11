package com.bielfernandezb.todoapp.presentation.new_task.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bielfernandezb.todoapp.core.Resource
import com.bielfernandezb.todoapp.domain.entity.Task
import com.bielfernandezb.todoapp.domain.use_case.InsertTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewTaskViewModel @Inject constructor(
    private val insertTaskUseCase: InsertTaskUseCase
) : ViewModel() {

    var task: MutableLiveData<Resource<Task?>> = MutableLiveData<Resource<Task?>>()

    fun saveTask(task: Task) {
        viewModelScope.launch {
            insertTaskUseCase.invoke(task)
        }
    }
}