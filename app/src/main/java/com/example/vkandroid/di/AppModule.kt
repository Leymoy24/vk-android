package com.example.vkandroid.di

import com.example.vkandroid.api.ApiService
import com.example.vkandroid.data.Repository
import com.example.vkandroid.data.RepositoryImpl
import com.example.vkandroid.data.SessionStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMainRepository(
        apiService: ApiService,
        sessionStorage: SessionStorage
    ): Repository {
        return RepositoryImpl(
            apiService = apiService,
            sessionStorage = sessionStorage
        )
    }

    @Provides
    @Singleton
    fun provideSessionStorage(): SessionStorage {
        return SessionStorage()
    }
}