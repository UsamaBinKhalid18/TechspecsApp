package com.example.techspecsapp.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class SearchResponse(val Success: Int, val data: SearchResponseData)

data class SearchResponseData(val results: List<SearchProduct>)

@Entity(tableName = "search_product")
data class SearchProduct(
    @Embedded(prefix = "model_")
    val model: Model,
    @Embedded(prefix = "brand_")
    val brand: Brand,
    @Embedded(prefix = "image_url_")
    val image_front: ImageUrl,
    @Embedded
    @PrimaryKey
    val _meta: SearchProductMeta
)

data class Model(@SerializedName("raw") val value: String)
data class Brand(@SerializedName("raw") val value: String)
data class ImageUrl(@SerializedName("raw") val value: String)
data class SearchProductMeta(val score: Float, val id: String)

@Entity(tableName = "user_product", primaryKeys =["productId","username"])
data class UserToProduct(val productId:String,
                         val username:String)