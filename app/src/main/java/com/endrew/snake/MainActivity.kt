package com.endrew.snake

import android.os.Bundle
import android.os.Vibrator
import android.os.VibratorManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.endrew.snake.ui.screen.SnakeGame
import com.endrew.snake.ui.screen.components.Controls
import com.endrew.snake.ui.theme.SnakeTheme

class MainActivity : ComponentActivity() {

    private lateinit var vibrator: Vibrator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val vibratorManager = getSystemService(VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibrator = vibratorManager.defaultVibrator

        val game = Game(lifecycleScope, vibrator)

        setContent {
            SnakeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SnakeGame(game)
                }
            }
        }
    }
}

