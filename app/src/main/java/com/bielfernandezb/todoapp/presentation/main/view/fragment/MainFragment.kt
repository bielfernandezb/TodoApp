package com.bielfernandezb.todoapp.presentation.main.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bielfernandezb.todoapp.core.Resource
import com.bielfernandezb.todoapp.databinding.FragmentMainBinding
import com.bielfernandezb.todoapp.domain.entity.Task
import com.bielfernandezb.todoapp.presentation.BaseFragment
import com.bielfernandezb.todoapp.presentation.main.adapter.TasksAdapter
import com.bielfernandezb.todoapp.presentation.main.viewmodel.MainViewModel
import com.bielfernandezb.todoapp.presentation.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment(), TasksAdapter.TaskItemListener {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: TasksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        setupRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = TasksAdapter(this)
        binding.tasksList.layoutManager = LinearLayoutManager(activity)
        binding.tasksList.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.tasks.observe(viewLifecycleOwner) {
            it?.let {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        it.data?.let { tasks ->
                            adapter.setItems(tasks as List<Task>)
                        }
                    }
                    Resource.Status.ERROR ->
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    else -> {}
                }
            }
        }
        binding.refreshLayout.setOnRefreshListener {
            viewModel.refreshTasks()
            binding.refreshLayout.isRefreshing = false
        }
        binding.fabAddTask.setOnClickListener {
            onNewTaskSelection()
        }
    }

    private fun onNewTaskSelection() {
        activity?.let { Navigator().navigateToNewTask(it) }
    }

    override fun onClickedTask(task: Task) {
        activity?.let { Navigator().navigateToNewTask(task, it) }
    }
}