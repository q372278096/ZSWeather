package com.zsweather.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class ZSWeatherApplication : Application(){

    companion object{
        //彩云api 令牌 免费 1w次
        const val TOKEN = "S0t4UlpVRXaiIasb"

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}