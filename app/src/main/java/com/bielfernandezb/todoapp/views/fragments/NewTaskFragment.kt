package com.bielfernandezb.todoapp.views.fragments

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bielfernandezb.todoapp.databinding.FragmentNewTaskBinding
import com.bielfernandezb.todoapp.model.entities.Task
import com.bielfernandezb.todoapp.utils.Resource
import com.bielfernandezb.todoapp.views.NewTaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewTaskFragment : Fragment() {

    private lateinit var binding: FragmentNewTaskBinding
    private val viewModel: NewTaskViewModel by viewModels()
    private var task: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments !== null) {
            task = arguments?.getParcelable("task")!!
            setValues(arguments?.getParcelable("task")!!)
        }
        setupObservers()
    }

    private fun setValues(task: Task) {
        binding.addTaskTitle.text =
            Editable.Factory.getInstance().newEditable(task.mTitle)
        binding.addTaskDescription.text =
            Editable.Factory.getInstance().newEditable(task.mDescription)
    }

    private fun setupObservers() {
        viewModel.task.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    if (it.data != null) {
                        binding.addTaskTitle.text =
                            Editable.Factory.getInstance().newEditable(it.data.mTitle)
                        binding.addTaskDescription.text =
                            Editable.Factory.getInstance().newEditable(it.data.mDescription)
                    }
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        })
        binding.fabSaveTask.setOnClickListener {
            if (task != null) {
                task!!.mTitle = binding.addTaskTitle.text.toString()
                task!!.mDescription = binding.addTaskDescription.text.toString()
                viewModel.saveTask(task!!)
            } else {
                viewModel.saveTask(
                    Task(
                        binding.addTaskTitle.text.toString(),
                        binding.addTaskDescription.text.toString()
                    )
                )
            }
            activity?.finish()
        }
    }
}