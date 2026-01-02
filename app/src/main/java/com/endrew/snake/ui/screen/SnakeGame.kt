package com.endrew.snake.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.endrew.snake.Game
import com.endrew.snake.ui.screen.components.Controls
import com.endrew.snake.ui.screen.components.GameBoard

@Composable
fun SnakeGame(game: Game) {
    val state = game.gameState.collectAsState(initial = null)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        state.value?.let {
            Text(
                text = "Score: ${it.score}",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Spacer(Modifier.height(10.dp))
            GameBoard(gameState = it)
        }

        Controls(
            modifier = Modifier.padding(24.dp),
            onDirectionChange = { selectedPos ->
                if (!selectedPos.isOpposite(game.lastPositionSelected)) {
                    game.lastPositionSelected = selectedPos
                    game.move = Pair(selectedPos.x, selectedPos.y)
                }
            }
        )
    }
}