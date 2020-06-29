package com.zsweather.android.logic.network

import com.zsweather.android.ZSWeatherApplication
import com.zsweather.android.logic.modle.DailyResponse
import com.zsweather.android.logic.modle.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {

    @GET("v2.5/${ZSWeatherApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(@Path("lng") lng: String, @Path("lat") lat: String):
            Call<RealtimeResponse>

    @GET("v2.5/${ZSWeatherApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String):
            Call<DailyResponse>
}