package com.bielfernandezb.todoapp.views.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bielfernandezb.todoapp.databinding.FragmentMainBinding
import com.bielfernandezb.todoapp.model.entities.Task
import com.bielfernandezb.todoapp.utils.Resource
import com.bielfernandezb.todoapp.views.MainViewModel
import com.bielfernandezb.todoapp.views.activities.NewTaskActivity
import com.bielfernandezb.todoapp.views.adapters.TasksAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(), TasksAdapter.TaskItemListener {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: TasksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        viewModel.tasks.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        if (it.data != null) {
                            adapter.setItems(it.data as ArrayList<Task>)
                        }
                    }
                    Resource.Status.ERROR ->
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
        binding.refreshLayout.setOnRefreshListener {
            viewModel.refreshTasks()
            binding.refreshLayout.isRefreshing = false
        }
        binding.fabAddTask.setOnClickListener {
            onNewTaskSelection()
        }
    }

    fun onNewTaskSelection() {
        val intent = Intent(activity, NewTaskActivity::class.java)
        startActivity(intent)
    }

    override fun onClickedTask(task: Task) {
        val intent = Intent(activity, NewTaskActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable("task", task)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}