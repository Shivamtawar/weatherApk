package com.example.weather_app.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather_app.model.QuoteResponse
import com.example.weather_app.network.RetrofitInstance
import kotlinx.coroutines.launch

@Composable
fun QuoteScreen() {

    var quote by remember { mutableStateOf<QuoteResponse?>(null) }
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
                text = "✦ Daily Quote",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = OrangeAccent,
                textAlign = TextAlign.Center
            )
            Text(
                text = "A spark for your day",
                fontSize = 13.sp,
                color = WhiteDim,
                letterSpacing = 2.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Quote card — show placeholder or fetched quote
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color(0xFF2A2A2A), Color(0xFF1A1A1A))
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                when {
                    isLoading -> {
                        CircularProgressIndicator(
                            color = OrangeAccent,
                            strokeWidth = 3.dp,
                            modifier = Modifier.size(42.dp)
                        )
                    }

                    quote != null -> {
                        Column(
                            modifier = Modifier.padding(28.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "\u201C",
                                fontSize = 72.sp,
                                fontWeight = FontWeight.Bold,
                                color = OrangeAccent,
                                lineHeight = 40.sp,
                                modifier = Modifier.offset(y = 12.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = quote!!.quote,
                                fontSize = 18.sp,
                                fontStyle = FontStyle.Italic,
                                color = White,
                                textAlign = TextAlign.Center,
                                lineHeight = 28.sp
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                            Divider(
                                color = OrangeAccent.copy(alpha = 0.4f),
                                thickness = 1.dp,
                                modifier = Modifier.fillMaxWidth(0.4f)
                            )
                            Text(
                                text = quote!!.author,
                                fontSize = 18.sp,
                                fontStyle = FontStyle.Italic,
                                color = White,
                                textAlign = TextAlign.Right,
                                lineHeight = 18.sp,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }

                    errorMsg != null -> {
                        Text(
                            text = errorMsg!!,
                            color = Color(0xFFFF4444),
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(24.dp)
                        )
                    }

                    else -> {
                        Text(
                            text = "Tap below to reveal\nyour quote of the moment.",
                            color = WhiteDim,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center,
                            lineHeight = 24.sp,
                            modifier = Modifier.padding(24.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Action button
            Button(
                onClick = {
                    scope.launch {
                        isLoading = true
                        errorMsg = null
                        quote = null
                        try {
//                            quote = RetrofitInstance.api.getWeather()
                        } catch (e: Exception) {
                            e.printStackTrace()
                            errorMsg = "Couldn't fetch a quote. Try again."
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
                Text(
                    text = if (quote != null) "New Quote" else "Get Quote",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 1.sp
                )
            }
        }
    }
}