package com.example.vkandroid.di

import android.app.Application
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
        application: Application,
        apiService: ApiService,
        sessionStorage: SessionStorage
    ): Repository {
        return RepositoryImpl(
            application = application,
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