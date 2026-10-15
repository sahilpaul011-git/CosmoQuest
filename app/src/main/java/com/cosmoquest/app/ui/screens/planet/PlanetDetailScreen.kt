
package com.cosmoquest.app.ui.screens.planet

import android.graphics.Color as AndroidColor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cosmoquest.app.data.model.Planet
import com.cosmoquest.app.data.repository.SolarSystemRepository

@Composable
fun PlanetDetailScreen(
    planetId: String,
    onBack: () -> Unit,
    onQuizClick: () -> Unit
) {
    val planet = SolarSystemRepository.getPlanetById(planetId)

    if (planet == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF07132E))
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Planet not found",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = onBack) {
                Text("Go Back")
            }
        }
        return
    }

    PlanetDetailContent(
        planet = planet,
        onBack = onBack,
        onQuizClick = onQuizClick
    )
}

@Composable
private fun PlanetDetailContent(
    planet: Planet,
    onBack: () -> Unit,
    onQuizClick: () -> Unit
) {
    val planetColor = runCatching {
        Color(AndroidColor.parseColor(planet.colorHex))
    }.getOrDefault(Color(0xFF9B7BFF))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF07132E))
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Text(
                    text = "←",
                    color = Color.White,
                    fontSize = 28.sp
                )
            }

            Text(
                text = "PLANET EXPLORER",
                color = Color(0xFF64D9FF),
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF111D3A)
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                planetColor.copy(alpha = 0.30f),
                                Color(0xFF111D3A)
                            )
                        )
                    )
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .background(
                                brush = Brush.radialGradient(
                                    colors = listOf(
                                        planetColor,
                                        planetColor.copy(alpha = 0.85f),
                                        Color(0xFF111D3A)
                                    )
                                ),
                                shape = CircleShape
                            )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = planet.name,
                        color = Color.White,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = planet.tagline,
                        color = Color(0xFFD8E4FF),
                        fontSize = 15.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "About this world",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = planet.description,
            color = Color(0xFFD0D8F0),
            fontSize = 15.sp,
            lineHeight = 24.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Planet facts",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        FactCard(
            title = "Diameter",
            value = "${planet.diameterKm} km",
            symbol = "↔"
        )

        FactCard(
            title = "Distance from the Sun",
            value = "${planet.distanceFromSunKm} km",
            symbol = "☀"
        )

        FactCard(
            title = "Orbital period",
            value = "${planet.orbitalPeriodDays} Earth days",
            symbol = "↻"
        )

        FactCard(
            title = "Length of a day",
            value = "${planet.dayLengthHours} hours",
            symbol = "◷"
        )

        FactCard(
            title = "Average temperature",
            value = "${planet.averageTemperatureC}°C",
            symbol = "♨"
        )

        FactCard(
            title = "Known moons",
            value = planet.moonCount.toString(),
            symbol = "☾"
        )

        FactCard(
            title = "Gravity",
            value = planet.gravity,
            symbol = "↓"
        )

        FactCard(
            title = "Atmosphere",
            value = planet.atmosphere,
            symbol = "☁"
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF1D2850)
            )
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "✨ DID YOU KNOW?",
                    color = Color(0xFF64D9FF),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = planet.funFact,
                    color = Color.White,
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        Button(
            onClick = onQuizClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = "Take the ${planet.name} Quiz 🚀",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
private fun FactCard(
    title: String,
    value: String,
    symbol: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF111D3A)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = symbol,
                color = Color(0xFF64D9FF),
                fontSize = 25.sp
            )

            Spacer(modifier = Modifier.size(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    color = Color(0xFFD0D8F0),
                    fontSize = 13.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = value,
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}
