package com.fee.aqarat.di

import com.fee.aqarat.data.remote.ApiService
import com.fee.aqarat.data.repoImpl.AuthRepoImpl
import com.fee.aqarat.domain.repo.AuthRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideAuthRepo(
        apiService: ApiService
    ): AuthRepo {
        return AuthRepoImpl(apiService)
    }
}