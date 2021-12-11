package com.bielfernandezb.todoapp.data.di

import android.content.Context
import com.bielfernandezb.todoapp.data.repository.RepositoryImpl
import com.bielfernandezb.todoapp.data.repository.local.LocalDataSource
import com.bielfernandezb.todoapp.data.repository.local.db.AppDatabase
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
    ) = RepositoryImpl(localDataSource)

    @JvmStatic
    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO
}