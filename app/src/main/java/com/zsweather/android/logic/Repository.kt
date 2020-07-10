package com.zsweather.android.logic

import android.content.Context
import androidx.lifecycle.liveData
import com.zsweather.android.logic.dao.PlaceDao
import com.zsweather.android.logic.modle.Place
import com.zsweather.android.logic.modle.Weather
import com.zsweather.android.logic.network.ZSWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.lang.Exception
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext
import kotlin.math.ln

object Repository {

    fun searchPlaces(query: String)  = fire(Dispatchers.IO){
            val placeResponse = ZSWeatherNetwork.searchPlaces(query)
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                Result.success(places)
            }else{
                Result.failure(RuntimeException("response status is " +
                        "${placeResponse.status}"))
            }
    }

    fun refreshWeather(lng: String, lat: String) = fire(Dispatchers.IO){
            coroutineScope {
                val deferredRealtime = async {
                    ZSWeatherNetwork.getRealtimeWeather(lng, lat)
                }
                val deferredDaily = async {
                    ZSWeatherNetwork.getDailyWeather(lng, lat)
                }
                val realtimeResponse = deferredRealtime.await()
                val dailyResponse = deferredDaily.await()
                if(realtimeResponse.status == "ok" && dailyResponse.status == "ok"){
                    val weather = Weather(realtimeResponse.result.realtime, dailyResponse.result.daily)
                    Result.success(weather)
                }else{
                    Result.failure(
                        RuntimeException(
                            "realtime response is ${realtimeResponse.status}" +
                                    "daily response is ${dailyResponse.status}"
                        )
                    )
                }
            }
    }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context){
            val result = try {
                block()
            }catch (e: Exception){
                Result.failure<T>(e)
            }
            emit(result)
        }

    fun savePlace(place: Place) = PlaceDao.savePlace(place)

    fun getPlace() = PlaceDao.getPlace()

    fun isPlaceSaved() = PlaceDao.isPlaceSaved()
}