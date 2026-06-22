package com.example.weather_app.network

import androidx.compose.ui.input.key.Key
import com.example.weather_app.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("current.json")
    suspend fun getWeather(

        @Query("key")
        apiKey: String,

        @Query("q")
        city: String
    ) : WeatherResponse

}