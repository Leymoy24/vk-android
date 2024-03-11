package com.example.vkandroid.data

import com.example.vkandroid.ProductUIModel
import com.example.vkandroid.api.ApiResult

interface Repository {
    suspend fun getProducts() : ApiResult<List<ProductUIModel>>
    suspend fun getProducts(skip: Int, category: String?): ApiResult<List<ProductUIModel>>
    suspend fun getProducts(query: String): ApiResult<List<ProductUIModel>>
    suspend fun getCategories(): ApiResult<List<String>>
    suspend fun getProductsByCategory(category: String): ApiResult<List<ProductUIModel>>

    fun getCurrentProduct(): ProductUIModel?
    fun setCurrentProduct(newProduct: ProductUIModel)
}