package com.matin.turbocar.ui.game.logic

import com.matin.turbocar.ui.game.model.Player
import com.matin.turbocar.ui.game.model.Size
import com.matin.turbocar.ui.game.model.ViewPort
import com.matin.turbocar.ui.game.utils.Direction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlayerLogic(
    private val scope: CoroutineScope,
    private val viewPort: ViewPort,
    private val size: Size,
) {
    private val screenHalf = viewPort.width / 2
    private val movementEdge = screenHalf * MOVEMENT_EDGE_RATIO

    private val _player = MutableStateFlow(
        Player(
            x = 0f,
            y = viewPort.height,
            offsetX = screenHalf,
            size = size
        )
    )
    val player = _player.asStateFlow()
    private val movement = flow {
        val stepPercents = listOf(0.1f, 0.2f, 0.4f) // Total is 0.7 or 70%
        stepPercents.forEach { stepPercent ->
            delay(MOVEMENT_DELAY_MS)
            emit(stepPercent * screenHalf)
        }
    }

    fun move(direction: Direction) {
        scope.launch {
            movement.collect { movement ->
                val newPosition =
                    (_player.value.x + movement * direction.value).coerceIn(
                        -movementEdge,
                        movementEdge
                    )
                _player.update { it.copy(x = newPosition) }
            }
        }
    }

    companion object {
        const val MOVEMENT_EDGE_RATIO = .7f
        const val MOVEMENT_DELAY_MS = 100L
    }
}