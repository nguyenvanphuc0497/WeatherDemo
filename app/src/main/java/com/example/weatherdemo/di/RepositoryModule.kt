package com.example.weatherdemo.di

import androidx.room.Room
import com.example.weatherdemo.data.local.LocalDB
import com.example.weatherdemo.data.repository.WeatherRepository
import com.example.weatherdemo.uitl.Constants
import org.koin.dsl.module

/**
 * Create by Nguyen Van Phuc on 3/11/21
 */
val repositoryModule = module {
    single { WeatherRepository(get()) }
}

val roomDbModule = module {
    single {
        Room.databaseBuilder(get(), LocalDB::class.java, Constants.Config.DATABASE)
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<LocalDB>().weatherDao() }
}
