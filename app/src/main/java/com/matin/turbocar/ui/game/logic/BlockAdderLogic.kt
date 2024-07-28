package com.matin.turbocar.ui.game.logic

import com.matin.turbocar.ui.game.model.Block
import com.matin.turbocar.ui.game.model.BlockFactoryProvider
import com.matin.turbocar.ui.game.model.ViewPort
import com.matin.turbocar.ui.game.utils.gameSpeed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.random.Random

class BlockAdderLogic(
    private val scope: CoroutineScope,
    private val blockLogic: BlockLogic,
    private val viewPort: ViewPort,
    private val blockFactoryProvider: BlockFactoryProvider,
    private val timerLogic: TimerLogic,
) {

    fun startBlockAdding() {
        scope.launch {
            manageAddingBlock()
        }
    }

    private suspend fun manageAddingBlock() {
        timerLogic.gameOver.collectLatest { isGameOver ->
            while (!isGameOver) {
                addBlock()
            }
        }
    }

    private suspend fun addBlock() {
        val randomTime = 700 + (Random.nextFloat() * 1000).toLong() + (300 - gameSpeed * 4)
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

    private fun createBlock(): Block {
        val randomBlock = Random.nextInt(1, 5)
        val factory = blockFactoryProvider.getBlockFactory(randomBlock)
        return factory.create(viewPort)
    }
}