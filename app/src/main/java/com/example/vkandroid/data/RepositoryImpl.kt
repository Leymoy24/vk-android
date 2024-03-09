package com.example.vkandroid.data

import android.app.Application
import com.example.vkandroid.ProductUIModel
import com.example.vkandroid.api.ApiResult
import com.example.vkandroid.api.ApiService

class RepositoryImpl(
    application: Application,
    private val apiService: ApiService,
    private val sessionStorage: SessionStorage
): Repository {
    override suspend fun getProducts(): ApiResult<List<ProductUIModel>> {
        return apiService.getProducts()
    }

    override suspend fun getProducts(skip: Int): ApiResult<List<ProductUIModel>> {
        return apiService.getProducts(skip)
    }

    override suspend fun getProducts(query: String): ApiResult<List<ProductUIModel>> {
        return apiService.getProducts(query)
    }

    override fun getCurrentProduct(): ProductUIModel? {
        return sessionStorage.currentProduct
    }

    override fun setCurrentProduct(newProduct: ProductUIModel) {
        sessionStorage.currentProduct = newProduct
    }
}