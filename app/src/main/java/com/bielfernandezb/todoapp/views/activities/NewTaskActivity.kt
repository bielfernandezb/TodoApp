package com.bielfernandezb.todoapp.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bielfernandezb.todoapp.R
import com.bielfernandezb.todoapp.model.entities.Task
import com.bielfernandezb.todoapp.views.fragments.NewTaskFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)
        val b = intent.extras
        if (b != null) {
            val value: Task? = b.getParcelable("task")
            val fragmentManager: FragmentManager = supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            val newTaskFragment = NewTaskFragment()
            val bundle = Bundle()
            bundle.putParcelable("task", value)
            newTaskFragment.arguments = bundle
            fragmentTransaction.add(R.id.new_main_content, newTaskFragment).commit()
        } else {
            val fragmentManager: FragmentManager = supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            val newTaskFragment = NewTaskFragment()
            fragmentTransaction.add(R.id.new_main_content, newTaskFragment).commit()
        }
    }
}