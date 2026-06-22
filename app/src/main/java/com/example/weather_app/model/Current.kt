package com.example.weather_app.model


data class Current(
    val temp_c : Double,
    val humidity : Int,
    val wind_kph : Double,
    val condition : Condition
)

data class Condition(
    val text : String,
    val icon : String
)

