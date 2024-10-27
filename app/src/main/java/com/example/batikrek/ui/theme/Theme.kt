package com.example.batikrek.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun BatikrekTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColorScheme(
            primary = Color(0xFF8B4513),
            secondary = Color(0xFFD4A24F),
            background = Color(0xFF303030),
            surface = Color.Black
        )
    } else {
        lightColorScheme(
            primary = Color(0xFF8B4513),
            secondary = Color(0xFFD4A24F),
            background = Color(0xFFFFF3E0),
            surface = Color.White
        )
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
