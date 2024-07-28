package com.matin.turbocar.ui.game.logic

import com.matin.turbocar.ui.game.model.Block
import com.matin.turbocar.ui.game.model.ViewPort
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BlockLogic(
    private val viewPort: ViewPort,
    private val blockSize: Float,
) : SyncLogic, RestartLogic {
    private val _blocks = MutableStateFlow<List<Block>>(listOf())
    val blocks = _blocks.asStateFlow()

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

    override fun onUpdate(time: Int) {
        val updatedBlocks = _blocks.value.map { block ->
            val newPosition = block.y + DEFAULT_OFFSET
            block.copy(y = newPosition)
        }
        _blocks.value = updatedBlocks
    }

    override fun onRestart() {
        _blocks.value = emptyList()
    }

    companion object {
        const val DEFAULT_OFFSET = 40f
    }
}