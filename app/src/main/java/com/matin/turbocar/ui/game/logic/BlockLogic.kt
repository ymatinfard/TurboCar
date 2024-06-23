package com.matin.turbocar.ui.game.logic

import com.matin.turbocar.ui.game.model.Block
import com.matin.turbocar.ui.game.model.ViewPort
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BlockLogic(
//    private val scope: CoroutineScope,
//    private val timerLogic: TimerLogic,
    private val viewPort: ViewPort,
    private val blockSize: Float,
) : GameLogic {
    private val _blocks = MutableStateFlow<List<Block>>(listOf())
    val blocks = _blocks.asStateFlow()

    init {
        blockPosition()
    }

    private fun blockPosition() {
        //     var newPosition = 0f
        //      scope.launch {
        // TimerLogic.startTimer().collect {
//                val updatedBlocks = _blocks.value.map { block ->
//                    val newPosition = block.y + DEFAULT_OFFSET
//                    block.copy(y = newPosition)
//                }
//                _blocks.value = updatedBlocks
        //          }
        //   }
    }

    fun addBlock(block: Block) {
        _blocks.update {
            it + block
        }
    }

    fun updateBlock(updateBlocks: List<Block>) {
        _blocks.update {
            updateBlocks
        }
    }

    companion object {
        const val DEFAULT_OFFSET = 40f
    }

    override fun onUpdate(time: Int) {
        val updatedBlocks = _blocks.value.map { block ->
            val newPosition = block.y + DEFAULT_OFFSET
            block.copy(y = newPosition)
        }
        _blocks.value = updatedBlocks
    }
}