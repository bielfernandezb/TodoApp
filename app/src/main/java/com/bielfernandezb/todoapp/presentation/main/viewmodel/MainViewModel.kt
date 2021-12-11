package com.bielfernandezb.todoapp.presentation.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bielfernandezb.todoapp.core.Resource
import com.bielfernandezb.todoapp.domain.entity.Task
import com.bielfernandezb.todoapp.domain.use_case.GetTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase
) : ViewModel() {

    var tasks: MutableLiveData<Resource<List<Task?>>> = MutableLiveData<Resource<List<Task?>>>()

    private fun getData() {
        viewModelScope.launch {
            getTasksUseCase.invoke(Unit).let {
                tasks.value = it
            }
        }
    }

    init {
        refreshTasks()
    }

    fun refreshTasks() {
        getData()
    }

}