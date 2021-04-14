package com.example.weatherdemo.di

import com.example.weatherdemo.BuildConfig
import com.example.weatherdemo.data.WeatherApiService
import com.example.weatherdemo.data.network.AuthInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Create by Nguyen Van Phuc on 3/11/21
 */
val networkModule = module {
    factory { AuthInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { provideWeatherApi(get()) }

    single { provideRetrofit(get()) }
}

fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
    val httpLogging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val interceptor = Interceptor { chain ->
        val original = chain.request()
        val request = original.newBuilder()
            .method(original.method, original.body)
            .build()
        chain.proceed(request)
    }

    return OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(httpLogging)
        .addInterceptor(interceptor)
        .build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

fun provideWeatherApi(retrofit: Retrofit): WeatherApiService {
    return retrofit.create(WeatherApiService::class.java)
}
