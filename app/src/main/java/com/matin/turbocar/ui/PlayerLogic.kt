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
    private val movementSpace = screenWidthPx.roundToInt() / 2
    private val movementEdge = (movementSpace * MOVEMENT_EDGE_RATIO).roundToInt()

    private val movement = flow {
        val stepPercents = listOf(0.1, 0.2, 0.4) // Total is 0.7 or 70%
        stepPercents.forEach { stepPercent ->
            delay(MOVEMENT_DELAY_MS)
            emit((stepPercent * movementSpace).toInt())
        }
    }

    fun move(direction: Direction) {
        scope.launch {
            movement.collect { movement ->
                val newPosition =
                    (_playerPosition.value.x + movement * direction.value).coerceIn(
                        -movementEdge,
                        movementEdge
                    )
                _playerPosition.update { it.copy(x = newPosition) }
            }
        }
    }

    companion object {
        const val MOVEMENT_EDGE_RATIO = .7
        const val MOVEMENT_DELAY_MS = 100L
    }
}