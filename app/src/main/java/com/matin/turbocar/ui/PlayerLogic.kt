package com.matin.turbocar.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class PlayerLogic(private val scope: CoroutineScope, private val screenWidthPx: Float) {
    private val _playerPosition = MutableStateFlow(Player(0))
    val playerPosition = _playerPosition.asStateFlow()
    private val screenEdge = screenWidthPx.roundToInt() / 2

    private val acceleration = flow {
        var accelerate = 40
        repeat(3) {
            delay(100)
            accelerate *= (it + 1)
            emit(accelerate)
        }
    }

    fun move(direction: Direction) {
        scope.launch {
            acceleration.collect { acceleration ->
                val newPosition =
                    (_playerPosition.value.x + acceleration * direction.value).coerceIn(
                        -screenEdge,
                        screenEdge
                    )
                _playerPosition.update { it.copy(x = newPosition) }
            }
        }
    }
}