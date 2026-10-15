
package com.cosmoquest.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val CosmoBackground = Color(0xFF07132E)
private val CosmoSurface = Color(0xFF111D3A)
private val CosmoPrimary = Color(0xFF9B7BFF)
private val CosmoSecondary = Color(0xFF64D9FF)
private val CosmoText = Color(0xFFF4F6FF)

private val CosmoColorScheme = darkColorScheme(
    primary = CosmoPrimary,
    onPrimary = Color(0xFF170B3B),
    secondary = CosmoSecondary,
    onSecondary = Color(0xFF00202B),
    background = CosmoBackground,
    onBackground = CosmoText,
    surface = CosmoSurface,
    onSurface = CosmoText,
    surfaceVariant = Color(0xFF253252),
    onSurfaceVariant = Color(0xFFD0D8F0),
    error = Color(0xFFFF6B78),
    onError = Color(0xFF410008)
)

@Composable
fun CosmoQuestTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = CosmoColorScheme,
        content = content
    )
}
