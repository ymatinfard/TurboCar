package com.matin.turbocar.ui.logic

import com.matin.turbocar.ui.model.Block
import com.matin.turbocar.ui.model.ViewPort
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BlockLogic(
    private val scope: CoroutineScope,
    private val timerLogic: TimerLogic,
    private val viewPort: ViewPort,
    private val blockSize: Float,
) {
    private val _block = MutableStateFlow(Block(y = 0f, startX = 0f, endX = blockSize))
    val block = _block.asStateFlow()

    init {
        blockPosition()
    }

    private fun blockPosition() {
        var newPosition = 0f
        scope.launch {
            timerLogic.time.collect {
                newPosition = _block.value.y + DEFAULT_OFFSET
                newPosition = if (newPosition > viewPort.height + DEFAULT_OFFSET) 0f else newPosition
                _block.update { it.copy(y = newPosition) }
            }
        }
    }

    companion object {
         val DEFAULT_OFFSET = 40f
    }
}