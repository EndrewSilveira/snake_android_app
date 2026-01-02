package com.endrew.snake.ui.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.endrew.snake.Game
import com.endrew.snake.model.state.GameState
import com.endrew.snake.ui.theme.Shapes

@Composable
fun GameBoard(
    modifier: Modifier = Modifier,
    gameState: GameState
) {
    BoxWithConstraints(
        modifier = modifier
            .padding(16.dp)
    ) {
        val tileSize = maxWidth / Game.BOARD_SIZE

        Box(
            modifier = Modifier
                .size(maxWidth)
                .border(2.dp, MaterialTheme.colorScheme.secondary)
        )

        Box(
            modifier = Modifier
                .offset(x = tileSize * gameState.food.first, y = tileSize * gameState.food.second)
                .size(tileSize)
                .background(color = MaterialTheme.colorScheme.secondary, CircleShape)
        )

        gameState.snake.forEach { snake ->
            Box(
                modifier = Modifier
                    .offset(x = tileSize * snake.first, y = tileSize * snake.second)
                    .size(tileSize)
                    .background(MaterialTheme.colorScheme.secondary, Shapes.small)
            )
        }
    }
}