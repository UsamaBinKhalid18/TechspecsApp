package com.example.techspecsapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.techspecsapp.data.SearchProduct

@Dao
interface SearchResponseDao {
    @Query("Select * from search_product")
    fun getAllResponses(): List<SearchProduct>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertResponse(searchResponse: SearchProduct)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllResponses(searchResponses: List<SearchProduct>)

    @Query("Delete from search_product")
    fun deleteAll()
}