package com.example.weatherdemo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherdemo.data.vo.City

/**
 * Create by Nguyen Van Phuc on 3/11/21
 */
@Database(entities = [City::class], version = 1, exportSchema = false)
abstract class LocalDB : RoomDatabase() {
    abstract fun weatherDao(): WeatherDAO
}
