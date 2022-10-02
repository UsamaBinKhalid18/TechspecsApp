package com.example.techspecsapp.data

import androidx.lifecycle.MutableLiveData
import com.example.techspecsapp.TechSpecAPI
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository private constructor() {
    private val httpLoggingInterceptor = HttpLoggingInterceptor()
        .also { it.level = HttpLoggingInterceptor.Level.BODY }
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(Interceptor { chain ->
            val request: Request =
                chain.request().newBuilder()
                    .addHeader("X-BLOBR-KEY", "UOPUMLudykKDLlXALrD52rKXtoxmRgkj").build()
            chain.proceed(request)
        }).build()
    private val techSpecAPI = Retrofit.Builder()
        .baseUrl("https://apis.dashboard.techspecs.io/f9g3z21kiqc0hz9s/en/v3/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(TechSpecAPI::class.java)
    val productsList = MutableLiveData<List<SearchProducts>>()
    fun searchProduct(productName: String, category: String) {
        val call = techSpecAPI.findProduct(productName, Category(category))
        call.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                val searchResponse = response.body()
                if (response.isSuccessful && searchResponse != null) {
                    productsList.value = searchResponse.data.results
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                productsList.value = emptyList()
            }
        })
    }

    companion object {
        @Volatile
        private var INSTANCE: Repository? = null
        fun getInstance(): Repository {
            val temp = INSTANCE
            if (temp != null) {
                return temp
            } else synchronized(this) {
                val instance = Repository()
                INSTANCE = instance
                return instance
            }
        }
    }
}