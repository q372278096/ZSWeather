package com.zsweather.android.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.zsweather.android.logic.Repository
import com.zsweather.android.logic.modle.Place

class PlaceViewModel: ViewModel() {
    private val searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<Place>()

    val placeLiveData = Transformations.switchMap(searchLiveData) { query ->

        Repository.searchPlaces(query)

    }

    fun searchPlaces(query: String){
       searchLiveData.value = query
    }

}


