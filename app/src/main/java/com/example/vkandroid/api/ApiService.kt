package com.example.vkandroid.api

import com.example.vkandroid.ProductUIModel

interface ApiService {
    suspend fun getProducts(): ApiResult<List<ProductUIModel>>
    suspend fun getProducts(skip: Int): ApiResult<List<ProductUIModel>>
    suspend fun getProducts(query: String): ApiResult<List<ProductUIModel>>
}