package com.example.vkandroid.data

import com.example.vkandroid.ProductUIModel
import com.example.vkandroid.api.ApiResult
import com.example.vkandroid.api.ApiService

class RepositoryImpl(
    private val apiService: ApiService,
    private val sessionStorage: SessionStorage
): Repository {
    override suspend fun getProducts(): ApiResult<List<ProductUIModel>> {
        return apiService.getProducts()
    }

    override suspend fun getProducts(skip: Int, category: String?): ApiResult<List<ProductUIModel>> {
        return apiService.getProducts(skip = skip, category = category)
    }

    override suspend fun getProducts(query: String): ApiResult<List<ProductUIModel>> {
        return apiService.getProducts(query = query)
    }

    override suspend fun getCategories(): ApiResult<List<String>> {
        return apiService.getCategories()
    }

    override suspend fun getProductsByCategory(category: String): ApiResult<List<ProductUIModel>> {
        return apiService.getProductsByCategory(category = category)
    }

    override fun getCurrentProduct(): ProductUIModel? {
        return sessionStorage.currentProduct
    }

    override fun setCurrentProduct(newProduct: ProductUIModel) {
        sessionStorage.currentProduct = newProduct
    }
}