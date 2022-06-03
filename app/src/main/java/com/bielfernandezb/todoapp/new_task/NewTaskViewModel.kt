package com.bielfernandezb.todoapp.new_task

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

    fun saveTask(task: Task) {
        viewModelScope.launch {
            repository.insertTask(task)
        }
    }
}