package com.example.watherapp


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun WeatherScreen(){
    var city by remember { mutableStateOf("nasik") }
    var temperature by remember { mutableStateOf<Float?>(null) }
    var description by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }

    LaunchedEffect(city) {
        try {
            val api = createApi()
            val response = api.getCurrentWeather("Your_API_KEY", city)
            temperature = response.current.temp_c
            description = response.current.condition.text
            location = "${response.location.name} , ${response.location.country}"

        }catch (e : Exception){
            temperature = null
            description = "Error : ${e.message}"
            location = ""

        }
    }

    Column(
       verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp).fillMaxSize()
    ) {
        Text(
            text = " Weather in $location :"
        )
        Spacer(modifier = Modifier.height(8.dp))

        temperature?. let{
            Text(" is $it°C ")
            Text("Condition is $description")

        } ?: Text ("Loading ......")
    }
}
