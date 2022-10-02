package com.example.techspecsapp.data

import com.google.gson.annotations.SerializedName


data class SearchResponse(val success: Int, val data: SearchResponseData)

data class SearchResponseData(val results: List<SearchProducts>)

data class SearchProducts(
    val model: Model,
    val brand: Brand,
    val image_front: ImageUrl,
    val _meta: SearchProductMeta
)

data class Model(@SerializedName("raw") val value: String)
data class Brand(@SerializedName("raw") val value: String)
data class ImageUrl(@SerializedName("raw") val value: String)
data class SearchProductMeta(val score: Float, val id: String)