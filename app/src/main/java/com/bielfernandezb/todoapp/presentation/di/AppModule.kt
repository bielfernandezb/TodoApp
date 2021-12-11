package com.bielfernandezb.todoapp.presentation.di

import com.bielfernandezb.todoapp.data.repository.RepositoryImpl
import com.bielfernandezb.todoapp.domain.use_case.GetTasksUseCase
import com.bielfernandezb.todoapp.domain.use_case.InsertTaskUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideGetTasksUseCase(repository: RepositoryImpl): GetTasksUseCase =
        GetTasksUseCase(repository)

    @Singleton
    @Provides
    fun provideInsertTasksUseCase(repository: RepositoryImpl): InsertTaskUseCase =
        InsertTaskUseCase(repository)

}