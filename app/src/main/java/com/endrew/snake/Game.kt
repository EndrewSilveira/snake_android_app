package com.endrew.snake

import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import com.endrew.snake.model.position.Position
import com.endrew.snake.model.state.GameState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.random.Random

class Game(private val scope: CoroutineScope, private val vibrator: Vibrator) {

    companion object {
        const val BOARD_SIZE = 16
    }

    private val mutex = Mutex()
    private val mutableGameState =
        MutableStateFlow(GameState(food = Pair(5, 5), snake = listOf(Pair(7, 7))))
    val gameState: Flow<GameState> = mutableGameState
    var lastPositionSelected = Position.RIGHT

    var move = Pair(1, 0)
        set(value) {
            scope.launch {
                mutex.withLock {
                    field = value
                }
            }
        }

    init {
        scope.launch {
            var snakeLength = 4
            var newScore = 0

            while (true) {
                delay(150)
                mutableGameState.update {
                    val newPosition = it.snake.first().let { pos ->
                        mutex.withLock {
                            Pair(
                                (pos.first + move.first + BOARD_SIZE) % BOARD_SIZE,
                                (pos.second + move.second + BOARD_SIZE) % BOARD_SIZE
                            )
                        }
                    }

                    if (newPosition == it.food) {
                        startVibration()
                        snakeLength++
                        newScore++
                    }

                    if (it.snake.contains(newPosition)) {
                        snakeLength = 4
                        newScore = 0
                    }

                    val newFoodPosition = if (newPosition == it.food) {
                        Pair(
                            Random.Default.nextInt(BOARD_SIZE),
                            Random.Default.nextInt(BOARD_SIZE)
                        )
                    } else {
                        it.food
                    }

                    it.copy(
                        food = newFoodPosition,
                        snake = listOf(newPosition) + it.snake.take(snakeLength - 1),
                        score = newScore
                    )
                }
            }
        }
    }

    private fun startVibration() {
        val vibrationDuration = 100L

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val vibrationEffect =
                VibrationEffect.createOneShot(vibrationDuration, VibrationEffect.DEFAULT_AMPLITUDE)
            vibrator.vibrate(vibrationEffect)
        } else {
            vibrator.vibrate(vibrationDuration)
        }
    }
}