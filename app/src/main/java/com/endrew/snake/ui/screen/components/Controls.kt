package com.endrew.snake.ui.screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.endrew.snake.model.position.Position
import com.endrew.snake.ui.theme.SnakeTheme

@Composable
fun Controls(
    modifier: Modifier = Modifier,
    onDirectionChange: (Position) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        DirectionButton(
            icon = Icons.Default.KeyboardArrowUp,
            contentDescription = "Arrow Up"
        ) {
            onDirectionChange(Position.UP)
        }
        Row {
            DirectionButton(
                icon = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                contentDescription = "Arrow Left"
            ) {
                onDirectionChange(Position.LEFT)
            }
            Spacer(modifier = Modifier.size(64.dp))
            DirectionButton(
                icon = Icons.AutoMirrored.Default.KeyboardArrowRight,
                contentDescription = "Arrow Right"
            ) {
                onDirectionChange(Position.RIGHT)
            }
        }
        DirectionButton(
            icon = Icons.Default.KeyboardArrowDown,
            contentDescription = "Arrow Down"
        ) {
            onDirectionChange(Position.DOWN)
        }

    }
}

@Preview
@Composable
private fun ControlsPreview() {
    SnakeTheme {
        Controls { }
    }
}