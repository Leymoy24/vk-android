package com.example.vkandroid.api

import com.example.vkandroid.ProductUIModel

interface ApiService {
    suspend fun getProducts(): ApiResult<List<ProductUIModel>>
}