package com.endrew.snake.model.position

enum class Position(val x: Int, val y: Int) {
    UP(x = 0, y = -1),
    DOWN(x = 0, y = 1),
    LEFT(x = -1, y = 0),
    RIGHT(x = 1, y = 0);

    fun isOpposite(other: Position): Boolean {
        return this.x == -other.x && this.y == -other.y
    }
}