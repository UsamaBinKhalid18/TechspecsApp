package com.example.techspecsapp.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class DetailResponse(val Success: Int, val data: DetailResponseData)

data class DetailResponseData(val product: List<ProductDetail>)

@Entity(tableName = "product_detail")
data class ProducDetailWrap(
    @Embedded val productDetail: ProductDetail,
    @PrimaryKey val id: String
)

data class ProductDetail(
    @Embedded(prefix = "camera_")
    @SerializedName("Camera") val camera: Camera?,
    @SerializedName("Design") val design: Map<String, String>?,
    @SerializedName("Display") val display: Map<String, String>?,
    @Embedded(prefix = "inside_")
    @SerializedName("Inside") val inside: Inside?,
)

data class Camera(
    @SerializedName("Back Camera") val backCamera: Map<String, String>?,
    @SerializedName("Front Camera") val frontCamera: Map<String, String>?,
    @SerializedName("Back Camera II") val backCamera2: Map<String, String>?,
    @SerializedName("Front Camera II") val frontCamera2: Map<String, String>?,
)

data class Inside(
    @SerializedName("Battery") val battery: Map<String, String>?,
    @SerializedName("Cellular") val cellular: Map<String, String>?,
    @SerializedName("Processor") val processor: Map<String, String>?,
    @SerializedName("RAM") val ram: Map<String, String>?,
    @SerializedName("Sensor(s)") val sensors: Map<String, String>?,
    @SerializedName("Software") val software: Map<String, String>?,
    @SerializedName("Storage") val storage: Map<String, String>?,
    @SerializedName("Wireless") val wireless: Map<String, String>?
)