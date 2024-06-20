package com.matin.turbocar.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class PlayerLogic(val scope: CoroutineScope, private val screenWidthPx: Float) {
    private val _playerPosition = MutableStateFlow(Player(0))
    val playerPosition = _playerPosition.asStateFlow()

    fun move(direction: Direction) {
        val offset = when (direction) {
            Direction.LEFT -> -DEFAULT_OFFSET
            Direction.RIGHT -> DEFAULT_OFFSET
        }

        scope.launch {
            repeat(3) {
                val screenHalf = screenWidthPx.roundToInt() / 2
                val newPosition =
                    (_playerPosition.value.x + offset).coerceIn(-screenHalf, screenHalf)
                _playerPosition.update { it.copy(x = newPosition) }
                delay(100)
            }
        }
    }

    companion object {
        const val DEFAULT_OFFSET = 100
    }
}