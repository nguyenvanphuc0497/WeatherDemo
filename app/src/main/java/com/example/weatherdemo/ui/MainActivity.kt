package com.example.weatherdemo.ui

import android.os.Bundle
import com.example.weatherdemo.R
import com.example.weatherdemo.extentions.replaceFragment
import com.example.weatherdemo.ui.base.BaseActivity
import com.example.weatherdemo.ui.home.HomeFragment

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        replaceFragment(HomeFragment(), addBackStack = false)
    }
}
