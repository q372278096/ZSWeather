package com.zsweather.android.logic.network

import com.zsweather.android.ZSWeatherApplication
import com.zsweather.android.logic.modle.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {

    @GET("v2/place?token=${ZSWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
}