package com.example.weather_app.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather_app.model.QuoteResponse
import com.example.weather_app.model.WeatherResponse
import com.example.weather_app.network.RetrofitInstance
import com.example.weather_app.util.Constants
import kotlinx.coroutines.launch

val OrangeAccent = Color(0xFFFF6B00)
val OrangeSoft = Color(0xFFFF8C3A)
val DarkBg = Color(0xFF121212)
val SurfaceDark = Color(0xFF1E1E1E)
val CardBg = Color(0xFF252525)
val White = Color(0xFFFFFFFF)
val WhiteDim = Color(0xFFB0B0B0)

@Composable
fun WeatherScreen() {

    var city by remember { mutableStateOf("") }
    var weather by remember { mutableStateOf<QuoteResponse?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMsg by remember { mutableStateOf<String?>(null) }

    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Header
            Text(
                text = "⛅ Weather",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = OrangeAccent,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Real-time forecasts",
                fontSize = 13.sp,
                color = WhiteDim,
                letterSpacing = 2.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(36.dp))

            // Search field
            OutlinedTextField(
                value = city,
                onValueChange = {
                    city = it
                    errorMsg = null
                },
                label = { Text("City name", color = WhiteDim) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = OrangeAccent,
                    unfocusedBorderColor = Color(0xFF3A3A3A),
                    focusedLabelColor = OrangeAccent,
                    cursorColor = OrangeAccent,
                    focusedTextColor = White,
                    unfocusedTextColor = White,
                    unfocusedContainerColor = SurfaceDark,
                    focusedContainerColor = SurfaceDark
                )
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Search button
            Button(
                onClick = {
                    if (city.isBlank()) {
                        errorMsg = "Please enter a city name."
                        return@Button
                    }
                    scope.launch {
                        isLoading = true
                        errorMsg = null
                        weather = null
                        try {
                            weather = RetrofitInstance.api.getWeather()
                        } catch (e: Exception) {
                            e.printStackTrace()
                            errorMsg = "City not found. Try again."
                        } finally {
                            isLoading = false
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = OrangeAccent,
                    contentColor = White
                ),
                enabled = !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(22.dp),
                        color = White,
                        strokeWidth = 2.5.dp
                    )
                } else {
                    Text(
                        "Search",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 1.sp
                    )
                }
            }

            // Error
            errorMsg?.let {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = it,
                    color = Color(0xFFFF4444),
                    fontSize = 13.sp,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

//            weather?.let {
//                WeatherCard(it)
//            }
        }
    }
}