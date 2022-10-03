package com.example.techspecsapp.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConverter {
    @TypeConverter
    fun toHashMap(value: String): Map<String, String>? =
        Gson().fromJson(value, object : TypeToken<Map<String, String>>() {}.type)

    @TypeConverter
    fun fromHashMap(value: Map<String, String>?): String =
        Gson().toJson(value)
}