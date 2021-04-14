package com.example.weatherdemo.ui.home

import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.weatherdemo.R
import com.example.weatherdemo.data.local.LocalDB
import com.example.weatherdemo.ui.base.BaseFragment
import com.example.weatherdemo.uitl.Constants
import com.example.weatherdemo.uitl.DebounceQueryTextListener
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Create by Nguyen Van Phuc on 4/14/21
 */
class HomeFragment : BaseFragment<HomeViewModel>() {

    private val homeListAdapter by lazy { HomeListAdapter() }

    override fun viewModel(): HomeViewModel = viewModel<HomeViewModel>().value

    override fun getLayoutResource(): Int = R.layout.fragment_home

    override fun initLiveDataObserver() {
        viewModel().getWeatherResult().observe(viewLifecycleOwner, {
            homeListAdapter.submitList(it)
        })
    }

    override fun initViews() {
        recyclerContent?.run {
            adapter = homeListAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
                setDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.divider_item_movie_color_gray,
                        null
                    )!!
                )
            })
        }
    }

    override fun initEvents() {
        searchView?.setOnQueryTextListener(DebounceQueryTextListener(viewLifecycleOwner.lifecycle) {
            if (it.isNullOrEmpty()) {
                viewModel().clearQuerySearch()
            } else {
                viewModel().getListCityBy(it)
            }
        })
    }
}
