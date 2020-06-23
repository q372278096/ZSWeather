package com.zsweather.android.logic

import androidx.lifecycle.liveData
import com.zsweather.android.logic.modle.Place
import com.zsweather.android.logic.network.ZSWeatherNetwork
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.lang.RuntimeException

object Repository {

    fun searchPlaces(query: String)  = liveData(Dispatchers.IO){
        val result = try {
            val placeResponse = ZSWeatherNetwork.searchPlaces(query)
            if (placeResponse.status == "ok") {
                val place = placeResponse.place
                Result.success(place)
            }else{
                Result.failure(RuntimeException("response status is " +
                        "${placeResponse.status}"))
            }
        }catch (e: Exception){
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
}