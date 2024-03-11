package com.example.vkandroid.api

import com.example.vkandroid.ProductUIModel

interface ApiService {
    suspend fun getProducts(): ApiResult<List<ProductUIModel>>
    suspend fun getProducts(skip: Int, category: String? = null): ApiResult<List<ProductUIModel>>
    suspend fun getProducts(query: String): ApiResult<List<ProductUIModel>>
    suspend fun getCategories(): ApiResult<List<String>>
    suspend fun getProductsByCategory(category: String): ApiResult<List<ProductUIModel>>
}