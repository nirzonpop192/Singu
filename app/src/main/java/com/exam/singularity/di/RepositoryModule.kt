package com.exam.singularity.di

import com.exam.singularity.ui.main.repository.MainRepository
import com.exam.singularity.ui.main.repository.MainRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindMainRepository(repo: MainRepositoryImp): MainRepository
}