package com.bielfernandezb.todoapp.views

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bielfernandezb.todoapp.model.entities.Task
import com.bielfernandezb.todoapp.model.repository.Repository
import com.bielfernandezb.todoapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewTaskViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    var task: MutableLiveData<Resource<Task?>> = MutableLiveData<Resource<Task?>>()

    fun getTaskById(taskId: Long) {
        viewModelScope.launch {
            repository.getTaskById(taskId).let {
                task.value = it
            }
        }
    }

    fun initialize(task: Task) {
        getTaskById(task.id)
    }


    fun saveTask(task: Task) {
        viewModelScope.launch {
            repository.insertTask(task)
        }
    }
}