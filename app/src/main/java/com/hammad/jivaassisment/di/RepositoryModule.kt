package com.hammad.jivaassisment.di

import com.hammad.jivaassisment.data.MockService
import com.hammad.jivaassisment.repository.CoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindCoreRepository(repository: CoreRepository.Impl): CoreRepository

    @Binds
    abstract fun bindMockService(service: MockService.Impl): MockService

}