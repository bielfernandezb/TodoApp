package com.bielfernandezb.todoapp.presentation.new_task.view.fragment

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bielfernandezb.todoapp.core.Resource
import com.bielfernandezb.todoapp.databinding.FragmentNewTaskBinding
import com.bielfernandezb.todoapp.domain.entity.Task
import com.bielfernandezb.todoapp.presentation.BaseFragment
import com.bielfernandezb.todoapp.presentation.navigation.Navigator.Companion.TASK
import com.bielfernandezb.todoapp.presentation.new_task.viewmodel.NewTaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewTaskFragment : BaseFragment() {

    private lateinit var binding: FragmentNewTaskBinding
    private val viewModel: NewTaskViewModel by viewModels()
    private var task: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBundle()
        setupObservers()
    }

    private fun getBundle() {
        arguments?.let {
            (it.getParcelable(TASK) as? Task)?.let { task ->
                setValues(task)
            }
        }
    }

    private fun setValues(task: Task) {
        binding.addTaskTitle.text =
            Editable.Factory.getInstance().newEditable(task.title)
        binding.addTaskDescription.text =
            Editable.Factory.getInstance().newEditable(task.description)
    }

    private fun setupObservers() {
        viewModel.task.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    if (it.data != null) {
                        binding.addTaskTitle.text =
                            Editable.Factory.getInstance().newEditable(it.data.title)
                        binding.addTaskDescription.text =
                            Editable.Factory.getInstance().newEditable(it.data.description)
                    }
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                else -> {}
            }
        }
        binding.fabSaveTask.setOnClickListener {
            saveTask()
            finish()
        }
    }

    private fun saveTask() {
        task?.let { task ->
            task.title = binding.addTaskTitle.text.toString()
            task.description = binding.addTaskDescription.text.toString()
            viewModel.saveTask(task)
        } ?: run {
            viewModel.saveTask(
                Task(
                    binding.addTaskTitle.text.toString(),
                    binding.addTaskDescription.text.toString()
                )
            )
        }
    }

    private fun finish() {
        activity?.finish()
    }
}