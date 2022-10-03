package com.example.techspecsapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.techspecsapp.data.ProducDetailWrap

@Dao
interface ProductDetailDao {
    @Query("Select * from product_detail p where p.id==:productId")
    fun getProductDetails(productId: String): List<ProducDetailWrap>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProductDetail(productDetail: ProducDetailWrap)
}