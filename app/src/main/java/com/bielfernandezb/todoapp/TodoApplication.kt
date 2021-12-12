package com.bielfernandezb.todoapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TodoApplication : Application() {
    private var mInstance: TodoApplication? = null

    @Synchronized
    fun getInstance(): TodoApplication? {
        return mInstance
    }

    override fun onCreate() {
        mInstance = this
        super.onCreate()
    }
}