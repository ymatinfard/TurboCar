package com.matin.turbocar.ui.game.logic

import com.matin.turbocar.ui.game.model.Player
import com.matin.turbocar.ui.game.model.Size
import com.matin.turbocar.ui.game.model.ViewPort
import com.matin.turbocar.ui.game.utils.Direction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

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

    fun move(direction: Direction) {
        val newPosition =
            (_player.value.x + (screenHalf * .7f) * direction.value).coerceIn(
                -movementEdge,
                movementEdge
            )
        _player.update { it.copy(x = newPosition) }
    }

    companion object {
        const val MOVEMENT_EDGE_RATIO = .7f
    }
}