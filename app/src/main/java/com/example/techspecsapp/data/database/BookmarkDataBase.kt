package com.example.techspecsapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.techspecsapp.data.ProducDetailWrap
import com.example.techspecsapp.data.SearchProduct
import com.example.techspecsapp.data.UserToProduct
import com.example.techspecsapp.data.database.dao.ProductDetailDao
import com.example.techspecsapp.data.database.dao.SearchResponseDao
import com.example.techspecsapp.data.database.dao.UserToProductDao

@Database(entities = [SearchProduct::class,ProducDetailWrap::class,UserToProduct::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class BookmarkDataBase:RoomDatabase() {

    abstract fun productDetailDao(): ProductDetailDao
    abstract fun searchResponseDao(): SearchResponseDao
    abstract fun userToProductDao():UserToProductDao

    companion object {
        @Volatile
        private var instance: BookmarkDataBase? = null

        fun getInstance(context: Context): BookmarkDataBase {
            val temp = instance
            if (temp != null) {
                return temp
            }
            synchronized(this) {
                val newtemp = Room.databaseBuilder(
                    context.applicationContext,
                    BookmarkDataBase::class.java,
                    "BookmarkDB"
                ).build()
                instance = newtemp
                return newtemp
            }
        }
    }
}