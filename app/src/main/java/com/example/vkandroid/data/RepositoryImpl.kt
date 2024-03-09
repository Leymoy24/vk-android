package com.example.vkandroid.data

import android.app.Application
import com.example.vkandroid.ProductUIModel
import com.example.vkandroid.api.ApiResult
import com.example.vkandroid.api.ApiService

class RepositoryImpl(
    application: Application,
    private val apiService: ApiService
): Repository {
    override suspend fun getProducts(): ApiResult<List<ProductUIModel>> {
        return apiService.getProducts()
    }
}