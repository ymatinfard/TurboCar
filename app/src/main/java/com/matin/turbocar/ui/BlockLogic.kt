package com.matin.turbocar.ui

import com.matin.turbocar.Block
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class BlockLogic(private val scope: CoroutineScope, private val screenHeightPx: Float) {

    private val _blockPosition = MutableStateFlow(Block(0))
    val blockPosition = _blockPosition.asStateFlow()

    init {
        blockPosition()
    }

    private fun blockPosition() {
        scope.launch {
            while (true) {
                val screenHeight = screenHeightPx.roundToInt()
                var newPosition = _blockPosition.value.y + DEFAULT_OFFSET
                newPosition = if (newPosition > screenHeight) 0 else newPosition
                _blockPosition.update { it.copy(y = newPosition) }
                delay(500)
            }
        }

    }

    companion object {
        const val DEFAULT_OFFSET = 100
    }

}