package com.bielfernandezb.todoapp.di

import android.content.Context
import com.bielfernandezb.todoapp.model.repository.Repository
import com.bielfernandezb.todoapp.model.repository.local.LocalDataSource
import com.bielfernandezb.todoapp.model.repository.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideTaskDao(db: AppDatabase) = db.taskDao()

    @Singleton
    @Provides
    fun provideRepository(
        localDataSource: LocalDataSource
    ) = Repository(localDataSource)

    @JvmStatic
    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO
}