package com.example.vkandroid.data

import com.example.vkandroid.ProductUIModel
import com.example.vkandroid.api.ApiResult

interface Repository {
    suspend fun getProducts() : ApiResult<List<ProductUIModel>>
}