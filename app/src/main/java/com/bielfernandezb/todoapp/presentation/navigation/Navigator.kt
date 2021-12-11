package com.bielfernandezb.todoapp.presentation.navigation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bielfernandezb.todoapp.R
import com.bielfernandezb.todoapp.domain.entity.Task
import com.bielfernandezb.todoapp.presentation.main.view.fragment.MainFragment
import com.bielfernandezb.todoapp.presentation.new_task.view.activity.NewTaskActivity
import com.bielfernandezb.todoapp.presentation.new_task.view.fragment.NewTaskFragment

class Navigator {

    companion object {
        const val TASK = "task"
    }

    fun navigateToNewTask(bundle: Bundle?, activity: AppCompatActivity) {
        bundle?.let {
            val fragmentManager: FragmentManager = activity.supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            val newTaskFragment = NewTaskFragment()
            newTaskFragment.arguments = it
            fragmentTransaction.add(R.id.newMainContent, newTaskFragment).commit()
        } ?: run {
            val fragmentManager: FragmentManager = activity.supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            val newTaskFragment = NewTaskFragment()
            fragmentTransaction.add(R.id.newMainContent, newTaskFragment).commit()
        }
    }

    fun navigateToNewTask(activity: FragmentActivity) {
        val intent = Intent(activity, NewTaskActivity::class.java)
        activity.startActivity(intent)
    }

    fun navigateToNewTask(task: Task, activity: FragmentActivity) {
        val intent = Intent(activity, NewTaskActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable(TASK, task)
        intent.putExtras(bundle)
        activity.startActivity(intent)
    }

    fun navigateToMainFragment(activity: AppCompatActivity) {
        val fragmentManager: FragmentManager = activity.supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val mainFragment = MainFragment()
        fragmentTransaction.add(R.id.mainContent, mainFragment).commit()
    }
}