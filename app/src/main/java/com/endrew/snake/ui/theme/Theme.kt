package com.endrew.snake.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = DarkGreen,
    secondary = DarkGreen,
    tertiary = DarkGreen,
    background = LightGreen
)

@Composable
fun SnakeTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content,
        shapes = Shapes
    )
}