package com.example.weatherdemo

import android.app.Application
import com.example.weatherdemo.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Create by Nguyen Van Phuc on 3/11/21
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        // Init DI
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}
