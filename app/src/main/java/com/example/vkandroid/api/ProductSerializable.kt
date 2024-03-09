package com.example.vkandroid.api

import com.example.vkandroid.ProductUIModel
import kotlinx.serialization.Serializable

@Serializable
data class ProductSerializable(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val discountPercentage: Float,
    val rating: Float,
    val stock: Int,
    val brand: String,
    val category: String,
    val thumbnail: String,
    val images: List<String>
) {
    fun convertToUIModel() = ProductUIModel(
        id,
        title,
        description,
        price,
        discountPercentage,
        rating,
        stock,
        brand,
        category,
        thumbnail,
        images
    )
}