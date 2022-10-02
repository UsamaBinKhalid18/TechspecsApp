package com.example.techspecsapp

import com.example.techspecsapp.data.Category
import com.example.techspecsapp.data.DetailResponse
import com.example.techspecsapp.data.SearchResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TechSpecAPI {
    @POST("product/search")
    fun findProduct(
        @Query("query") productName: String,
        @Body category:Category
    ): Call<SearchResponse>

    @GET("product/detail")
    fun getProductDetail(@Query("productId") productId:String):Call<DetailResponse>

}