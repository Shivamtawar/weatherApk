package com.example.weather_app.network

import com.example.weather_app.model.QuoteResponse
import retrofit2.http.GET

interface QuoteApi {


    @GET("quotes/random")
    suspend fun getWeather(): QuoteResponse
}