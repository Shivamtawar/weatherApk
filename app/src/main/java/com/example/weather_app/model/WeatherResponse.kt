package com.example.weather_app.model


data class WeatherResponse(
    val location : Location,
    val current : Current
)

data class Location(
    val name : String,
    val country : String
)
