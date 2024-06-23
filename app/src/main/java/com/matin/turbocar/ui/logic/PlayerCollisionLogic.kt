package com.matin.turbocar.ui.logic

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PlayerCollisionLogic(
    private val playerLogic: PlayerLogic,
    private val blockLogic: BlockLogic,
    private val timerLogic: TimerLogic,
    private val scope: CoroutineScope,
) {
    private val _collisionHappened = MutableStateFlow(false)
    val collisionHappened = _collisionHappened.asStateFlow()

    init {
        scope.launch {
            timerLogic.startTimer().collect {
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
    }
}