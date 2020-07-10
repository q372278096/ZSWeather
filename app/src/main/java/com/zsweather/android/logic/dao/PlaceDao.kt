package com.zsweather.android.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.zsweather.android.ZSWeatherApplication
import com.zsweather.android.logic.modle.Place

object PlaceDao {

    fun savePlace(place: Place){
        sharePreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }

    fun getPlace(): Place{
        val placeJson = sharePreferences().getString("place", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }

    fun isPlaceSaved() = sharePreferences().contains("place")

    private fun sharePreferences() = ZSWeatherApplication.context.
        getSharedPreferences("zs_weather", Context.MODE_PRIVATE) 
}