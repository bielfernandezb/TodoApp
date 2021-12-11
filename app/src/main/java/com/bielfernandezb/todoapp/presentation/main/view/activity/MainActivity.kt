package com.bielfernandezb.todoapp.presentation.main.view.activity

import android.os.Bundle
import com.bielfernandezb.todoapp.R
import com.bielfernandezb.todoapp.presentation.BaseActivity
import com.bielfernandezb.todoapp.presentation.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Navigator().navigateToMainFragment(this)
    }
}