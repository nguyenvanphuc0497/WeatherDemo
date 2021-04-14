package com.example.weatherdemo.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherdemo.data.vo.City

/**
 * Create by Nguyen Van Phuc on 4/14/21
 */
@Dao
interface WeatherDAO {
    @Query("SELECT EXISTS (SELECT 1 FROM city WHERE title LIKE '%' || :textQuery || '%') ")
    suspend fun isCityExists(textQuery: String): Boolean

    @Query("SELECT * FROM city WHERE title LIKE '%' || :textQuery || '%' ")
    suspend fun getListCityBy(textQuery: String): List<City>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cities: List<City>)
}
