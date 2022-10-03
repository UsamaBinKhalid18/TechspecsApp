package com.example.techspecsapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.techspecsapp.data.ProducDetailWrap
import com.example.techspecsapp.data.SearchProduct

@Database(
    entities = [SearchProduct::class, ProducDetailWrap::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDetailDao(): ProductDetailDao
    abstract fun searchResponseDao(): SearchResponseDao

    companion object {
        @Volatile
        private var instance: ProductDatabase? = null

        fun getInstance(context: Context): ProductDatabase {
            val temp = instance
            if (temp != null) {
                return temp
            }
            synchronized(this) {
                val newtemp = Room.databaseBuilder(
                    context.applicationContext,
                    ProductDatabase::class.java,
                    "ProductDB"
                ).build()
                instance = newtemp
                return newtemp
            }
        }
    }
}