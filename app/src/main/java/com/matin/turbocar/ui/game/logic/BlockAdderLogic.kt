package com.matin.turbocar.ui.game.logic

import com.matin.turbocar.ui.game.model.Block
import com.matin.turbocar.ui.game.model.BlockFactoryProvider
import com.matin.turbocar.ui.game.model.ViewPort
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class BlockAdderLogic(
    private val scope: CoroutineScope,
    private val blockLogic: BlockLogic,
    private val viewPort: ViewPort,
    private val blockFactoryProvider: BlockFactoryProvider,
) {
    fun startBlockAdding() {
        scope.launch {
            addBlock()
        }
    }

    private suspend fun addBlock() {
        while (true) {
            val randomTime = 2000 + (Random.nextFloat() * 1000).toLong()
            delay(randomTime)

            val blocks = blockLogic.blocks.value

            if (blocks.size < 20) {
                blockLogic.addBlock(createBlock())
            } else {
                val destructedBlock = blocks.firstOrNull { it.y > viewPort.height }
                if (destructedBlock != null) {
                    println("Updating block with uid: ${destructedBlock.uid}")
                    val updatedBlocks = blocks.map { block ->
                        if (block.uid == destructedBlock.uid) createBlock() else block
                    }
                    blockLogic.updateBlock(updatedBlocks)
                }
            }
        }
    }

    private fun createBlock(): Block {
        val randomBlock = Random.nextInt(1, 5)
        val factory = blockFactoryProvider.getBlockFactory(randomBlock)
        return factory.create(viewPort)
    }
}