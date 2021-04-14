package com.example.weatherdemo.di

import com.example.weatherdemo.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Create by Nguyen Van Phuc on 3/11/21
 */
val viewModelModule = module {
    viewModel { HomeViewModel() }
}
