package com.example.weather_app.network

import com.example.weather_app.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {

    @GET("pokemon/{name}")
    suspend fun getPokemon(
        @Path("name")
        pokemonName : String
    ): PokemonResponse
}