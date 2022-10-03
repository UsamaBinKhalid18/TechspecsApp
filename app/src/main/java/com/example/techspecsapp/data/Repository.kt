package com.example.techspecsapp.data

import androidx.lifecycle.MutableLiveData
import com.example.techspecsapp.TechSpecAPI
import com.example.techspecsapp.data.database.BookmarkDataBase
import com.example.techspecsapp.data.database.ProductDatabase
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository private constructor(private val database: ProductDatabase,val bookmarkDataBase: BookmarkDataBase) {
    var username:String=""
    private val httpLoggingInterceptor = HttpLoggingInterceptor()
        .also { it.level = HttpLoggingInterceptor.Level.BODY }
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(Interceptor { chain ->
            val request: Request =
                chain.request().newBuilder()
                    .addHeader("X-BLOBR-KEY", "ZZsZnG4vTzLFuYoDxE82s2nrFbeJgE2C").build()
            chain.proceed(request)
        }).build()
    private val techSpecAPI = Retrofit.Builder()
        .baseUrl("https://apis.dashboard.techspecs.io/f9g3z21kiqc0hz9s/en/v3/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(TechSpecAPI::class.java)
    val productsList = MutableLiveData<List<SearchProduct>>()

    fun searchProduct(productName: String) {
        val call = techSpecAPI.findProduct(productName, Category("smartphone"))
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

    var productDetail = MutableLiveData<ProductDetail>()

    fun productDetail(productId: String) {
        val call = techSpecAPI.getProductDetail(productId)
        call.enqueue(object : Callback<DetailResponse> {
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                productDetail.value = response.body()!!.data.product[0]
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {

            }

        })
    }

    fun productDetailOffline(productId: String): ProductDetail {
        val wrap = database.productDetailDao().getProductDetails(productId)
        return wrap[0].productDetail
    }

    fun insertProduct(searchProduct: SearchProduct, detail: ProductDetail) {
        database.searchResponseDao().insertResponse(searchProduct)
        database.productDetailDao()
            .insertProductDetail(ProducDetailWrap(detail, searchProduct._meta.id))
    }

    fun getAllResponsesFromDB(): List<SearchProduct> {
        return database.searchResponseDao().getAllResponses()
    }

    fun getBookmarkResponses(): List<SearchProduct> {
        return bookmarkDataBase.userToProductDao().getBookmarkSearchResponse(username)
    }

    fun insertBookmark(item: SearchProduct) {
        bookmarkDataBase.userToProductDao().insertUserToProduct(UserToProduct(item._meta.id,username))
        bookmarkDataBase.productDetailDao().insertProductDetail(ProducDetailWrap(productDetail.value!!,item._meta.id))
        bookmarkDataBase.searchResponseDao().insertResponse(item)
    }

    companion object {
        @Volatile
        private var INSTANCE: Repository? = null
        fun getInstance(database: ProductDatabase,bookmarkDataBase: BookmarkDataBase): Repository {
            val temp = INSTANCE
            if (temp != null) {
                return temp
            } else synchronized(this) {
                val instance = Repository(database,bookmarkDataBase)
                INSTANCE = instance
                return instance
            }
        }
    }
}