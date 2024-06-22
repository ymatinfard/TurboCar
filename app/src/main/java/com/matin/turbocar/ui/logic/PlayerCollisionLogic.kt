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

    val _collisionHappend = MutableStateFlow(false)
    val collisionHappened = _collisionHappend.asStateFlow()

    init {
        scope.launch {
            timerLogic.time.collect {
                val playerRect = playerLogic.player.value.rect
                val blockRect = blockLogic.block.value.rect

                if (playerRect.overlaps(blockRect)) {
                    _collisionHappend.value = true
                    println("COLLISION------------------")
                } else {
                    _collisionHappend.value = false
                }
            }
        }
    }
}