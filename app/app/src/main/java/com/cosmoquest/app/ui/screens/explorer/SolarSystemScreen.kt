
package com.cosmoquest.app.ui.screens.explorer

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cosmoquest.app.data.model.Planet
import com.cosmoquest.app.data.repository.SolarSystemRepository

@Composable
fun SolarSystemScreen(
    onPlanetClick: (String) -> Unit,
    onBack: () -> Unit
) {
    val planets = SolarSystemRepository.planets

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF07132E))
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(onClick = onBack) {
                    Text("← Back")
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "COSMIC EXPLORER",
                    color = Color(0xFFBBA4FF),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Our Solar System",
                color = Color.White,
                fontSize = 29.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Travel through eight planets orbiting our Sun.",
                color = Color(0xFFB7C4E4),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            SolarSystemIllustration()

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Select a planet to begin your mission.",
                color = Color(0xFF64D9FF),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }

        items(planets, key = { it.id }) { planet ->
            PlanetCard(
                planet = planet,
                onClick = { onPlanetClick(planet.id) }
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun SolarSystemIllustration() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF0B1836)
        )
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val center = Offset(
                x = size.width / 2f,
                y = size.height / 2f
            )

            val maxRadius = size.width.coerceAtMost(size.height) * 0.43f

            for (i in 1..4) {
                val radius = maxRadius * i / 4f

                drawCircle(
                    color = Color(0xFF52648F).copy(alpha = 0.55f),
                    radius = radius,
                    center = center,
                    style = Stroke(width = 1.dp.toPx())
                )
            }

            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFFFFFF9A),
                        Color(0xFFFFB52E),
                        Color(0xFFEE6C19)
                    ),
                    center = center,
                    radius = 30.dp.toPx()
                ),
                radius = 15.dp.toPx(),
                center = center
            )

            val planetColors = listOf(
                Color(0xFFAAAAAA),
                Color(0xFFE8B878),
                Color(0xFF3988FF),
                Color(0xFFE66B4E),
                Color(0xFFD9A77B),
                Color(0xFFE6D19A),
                Color(0xFF8BDDE0),
                Color(0xFF4169E1)
            )

            for (i in 0 until 8) {
                val angle = i * 0.72f + 0.5f
                val radius = maxRadius * (0.25f + i * 0.105f)

                val x = center.x + kotlin.math.cos(angle) * radius
                val y = center.y + kotlin.math.sin(angle) * radius

                drawCircle(
                    color = planetColors[i],
                    radius = (4 + i % 3).dp.toPx(),
                    center = Offset(x, y)
                )
            }

            // Decorative stars
            val stars = listOf(
                Offset(size.width * 0.12f, size.height * 0.18f),
                Offset(size.width * 0.82f, size.height * 0.16f),
                Offset(size.width * 0.88f, size.height * 0.72f),
                Offset(size.width * 0.17f, size.height * 0.81f),
                Offset(size.width * 0.68f, size.height * 0.88f)
            )

            stars.forEach {
                drawCircle(
                    color = Color.White.copy(alpha = 0.8f),
                    radius = 1.5.dp.toPx(),
                    center = it
                )
            }
        }
    }
}

@Composable
private fun PlanetCard(
    planet: Planet,
    onClick: () -> Unit
) {
    val planetColor = rememberPlanetColor(planet.colorHex)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF152343)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(62.dp)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                planetColor,
                                planetColor.copy(alpha = 0.65f),
                                Color(0xFF0B1836)
                            )
                        ),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .background(
                            planetColor,
                            CircleShape
                        )
                )
            }

            Spacer(modifier = Modifier.size(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = planet.name,
                    color = Color.White,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = planet.tagline,
                    color = Color(0xFFBBA4FF),
                    fontSize = 13.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Diameter: ${"%,d".format(planet.diameterKm)} km",
                    color = Color(0xFFD0D8F0),
                    fontSize = 12.sp
                )
            }

            Text(
                text = "›",
                color = Color(0xFF64D9FF),
                fontSize = 32.sp
            )
        }
    }
}

@Composable
private fun rememberPlanetColor(hex: String): Color {
    val parsed = runCatching {
        Color(android.graphics.Color.parseColor(hex))
    }.getOrElse {
        MaterialTheme.colorScheme.primary
    }

    return parsed
}
