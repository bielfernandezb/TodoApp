package com.bielfernandezb.todoapp.main

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
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    var tasks: MutableLiveData<Resource<List<Task?>>> = MutableLiveData<Resource<List<Task?>>>()

    fun getData() {
        viewModelScope.launch {
            repository.getTasks().let {
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