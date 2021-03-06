package com.example.weatherdemo.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentActivity

/**
 * Create by Nguyen Van Phuc on 3/11/21
 */
abstract class BaseActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // full screen
        //window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        // set Content View
        createViewForActivity(savedInstanceState)
    }

    open fun createViewForActivity(savedInstanceState: Bundle?) {
        getLayoutResource()?.run { setContentView(this) }
    }

    @LayoutRes
    open fun getLayoutResource(): Int? = null
}
