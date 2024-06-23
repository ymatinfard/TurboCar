package com.matin.turbocar.ui.game.logic

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PlayerCollisionLogic(
    private val playerLogic: PlayerLogic,
    private val blockLogic: BlockLogic,
): GameLogic {
    private val _collisionHappened = MutableStateFlow(false)
    val collisionHappened = _collisionHappened.asStateFlow()

    override fun onUpdate(time: Int) {
        val playerRect = playerLogic.player.value.rect
        val blocks = blockLogic.blocks.value
        blocks.forEach { block ->
            if (playerRect.overlaps(block.rect)) {
                _collisionHappened.value = true
                println("COLLISION------------------")
                //    timerLogic.endGame()
            } else {
                // TODO() not needed
                _collisionHappened.value = false
            }
        }
    }
}