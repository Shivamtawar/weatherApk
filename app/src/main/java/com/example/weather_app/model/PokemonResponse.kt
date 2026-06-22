package com.example.weather_app.model

data class PokemonResponse(

    val name : String,
    val height : Int,
    val weight : Int,
    val stats : List<Stat>,
    val sprites : Sprites
)

data class Sprites(
    val front_default: String
)

data class Stat(
    val base_stat : Int,
    val stat: StatInfo
)

data class StatInfo(
    val name : String
)
