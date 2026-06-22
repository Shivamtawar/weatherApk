package com.example.weather_app.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weather_app.model.PokemonResponse

@Composable
fun PokemonCard(pokemon: PokemonResponse) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF2A2A2A), Color(0xFF1A1A1A))
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Sprite
            Box(
                modifier = Modifier
                    .size(160.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF1E1E1E)),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = pokemon.sprites.front_default,
                    contentDescription = pokemon.name,
                    modifier = Modifier.size(140.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Name
            Text(
                text = pokemon.name.replaceFirstChar { it.uppercase() },
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                color = OrangeAccent,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Height / Weight chips
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatChip(label = "Height", value = "${pokemon.height}")
                StatChip(label = "Weight", value = "${pokemon.weight}")
            }

            Spacer(modifier = Modifier.height(20.dp))
            Divider(color = Color(0xFF333333), thickness = 1.dp)
            Spacer(modifier = Modifier.height(16.dp))

            // Stats header
            Text(
                text = "Base Stats",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = OrangeSoft,
                letterSpacing = 2.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Stat bars
            pokemon.stats.forEach { statEntry ->
                StatBar(
                    name = statEntry.stat.name.replace("-", " "),
                    value = statEntry.base_stat
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun StatChip(label: String, value: String) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF2E2E2E))
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = White)
        Text(text = label, fontSize = 11.sp, color = WhiteDim, letterSpacing = 1.sp)
    }
}

@Composable
fun StatBar(name: String, value: Int) {
    val maxStat = 255f
    val fraction = (value / maxStat).coerceIn(0f, 1f)

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name.replaceFirstChar { it.uppercase() },
            fontSize = 12.sp,
            color = WhiteDim,
            modifier = Modifier.width(100.dp)
        )
        Text(
            text = "$value",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = White,
            modifier = Modifier.width(36.dp),
            textAlign = TextAlign.End
        )
        Spacer(modifier = Modifier.width(10.dp))
        Box(
            modifier = Modifier
                .weight(1f)
                .height(6.dp)
                .clip(RoundedCornerShape(3.dp))
                .background(Color(0xFF333333))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(fraction)
                    .clip(RoundedCornerShape(3.dp))
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(OrangeAccent, OrangeSoft)
                        )
                    )
            )
        }
    }
}