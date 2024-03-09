package com.example.vkandroid.api

import kotlinx.serialization.Serializable

@Serializable
data class ListOfProductsSerializable(
    val products: List<ProductSerializable>
)