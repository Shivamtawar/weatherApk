package com.example.weather_app.model


data class Current(
    val temp_c : Double,
    val humidity : Int,
    val wind_kph : Double,
    val condition : Condition,
    val cloud : Int,
    val feelslike_c : Double
)

data class Condition(
    val text : String,
    val icon : String
)

