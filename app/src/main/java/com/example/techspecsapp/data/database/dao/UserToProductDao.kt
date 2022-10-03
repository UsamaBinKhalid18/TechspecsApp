package com.example.techspecsapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.techspecsapp.data.SearchProduct
import com.example.techspecsapp.data.UserToProduct

@Dao
interface UserToProductDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUserToProduct(userToProduct: UserToProduct)

    @Query("Select * from search_product s where s.id in (Select u.productId from user_product u where u.username==:username) ")
    fun getBookmarkSearchResponse(username:String):List<SearchProduct>
}