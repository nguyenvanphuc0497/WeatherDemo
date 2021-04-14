package com.example.weatherdemo.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherdemo.R
import com.example.weatherdemo.data.vo.City
import com.example.weatherdemo.ui.base.BaseListAdapter
import kotlinx.android.synthetic.main.item_home_weather.view.*

/**
 * Create by Nguyen Van Phuc on 4/14/21
 */
class HomeListAdapter : BaseListAdapter<City>(WeatherDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        WeatherViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_weather, parent, false)
        )


    inner class WeatherViewHolder(view: View) : BaseViewHolder(view) {
        override fun onBind() {
            itemView.run {
                getItem(adapterPosition).let { item ->
                    tvTitleContent?.text = item.title
                    tvLatLongContent?.text = item.latLong
                    tvTypeContent?.text = item.locationType
                    tvWoEIDContent?.text = item.woeId.toString()
                }
            }
        }
    }

    class WeatherDiffCallback : BaseDiffUtilItemCallback<City>()
}