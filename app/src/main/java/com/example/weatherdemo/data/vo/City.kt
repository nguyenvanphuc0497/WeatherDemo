package com.example.weatherdemo.data.vo

import androidx.room.Entity
import androidx.room.Index
import com.google.gson.annotations.SerializedName

/**
 * Create by Nguyen Van Phuc on 4/13/21
 */
@Entity(
    indices = [Index("title")],
    primaryKeys = ["title"]
)
data class City(
    val title: String,
    @SerializedName("location_type") val locationType: String,
    @SerializedName("latt_long") val latLong: String,
    @SerializedName("woeid") val woeId: Int,
)
