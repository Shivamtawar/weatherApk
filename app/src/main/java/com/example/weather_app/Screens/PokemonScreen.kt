package com.example.weather_app.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather_app.model.PokemonResponse
import com.example.weather_app.network.RetrofitInstance
import kotlinx.coroutines.launch

@Composable
fun PokemonScreen() {

    var pokemonName by remember { mutableStateOf("") }
    var pokemon by remember { mutableStateOf<PokemonResponse?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMsg by remember { mutableStateOf<String?>(null) }

    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp, vertical = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Header
            Text(
                text = "Pokédex",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = OrangeAccent,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Search any Pokémon",
                fontSize = 13.sp,
                color = WhiteDim,
                letterSpacing = 2.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(36.dp))

            // Search field
            OutlinedTextField(
                value = pokemonName,
                onValueChange = {
                    pokemonName = it
                    errorMsg = null
                },
                label = { Text("Pokémon name or ID", color = WhiteDim) },
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
                    if (pokemonName.isBlank()) {
                        errorMsg = "Please enter a Pokémon name or ID."
                        return@Button
                    }
                    scope.launch {
                        isLoading = true
                        errorMsg = null
                        pokemon = null
                        try {
                            pokemon = RetrofitInstance.api.getPokemon(pokemonName.lowercase().trim())
                        } catch (e: Exception) {
                            e.printStackTrace()
                            errorMsg = "Pokémon not found. Check the name and try again."
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
                        text = "Search",
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

            pokemon?.let {
                PokemonCard(it)
            }
        }
    }
}