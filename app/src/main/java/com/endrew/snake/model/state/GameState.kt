package com.endrew.snake.model.state

data class GameState(
    val food: Pair<Int, Int>,
    val snake: List<Pair<Int, Int>>,
    val score: Int = 0
)