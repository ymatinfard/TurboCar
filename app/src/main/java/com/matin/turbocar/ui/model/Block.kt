package com.matin.turbocar.ui.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import com.matin.turbocar.ui.Direction
import java.util.UUID

data class Block(
    val uid: UUID = UUID.randomUUID(),
    val y: Float,
    val startX: Float,
    val endX: Float,
    val direction: Direction,
) {
    val rect = Rect(topLeft = Offset(startX, y), bottomRight = Offset(endX, y))
}

interface BlockFactory {
    fun create(viewPort: ViewPort): Block
}

class LeftBlockFactory : BlockFactory {
    override fun create(viewPort: ViewPort): Block {
        val blockSize = viewPort.width / 3
        return Block(y = 0f, startX = 0f, endX = blockSize, direction = Direction.LEFT)
    }
}

class CenterBlockFactory : BlockFactory {
    override fun create(viewPort: ViewPort): Block {
        val blockSize = viewPort.width / 3
        return Block(
            y = 0f,
            startX = (viewPort.width / 2) - (blockSize / 2),
            endX = (viewPort.width / 2) + blockSize,
            direction = Direction.CENTER
        )
    }
}

class RightBlockFactory : BlockFactory {
    override fun create(viewPort: ViewPort): Block {
        val blockSize = viewPort.width / 3
        return Block(
            y = 0f,
            startX = (viewPort.width - blockSize),
            endX = viewPort.width,
            direction = Direction.RIGHT
        )
    }
}

object BlockFactoryProvider {
    fun getBlockFactory(randomBlock: Int): BlockFactory = when (randomBlock) {
        1 -> LeftBlockFactory()
        2 -> CenterBlockFactory()
        3 -> RightBlockFactory()
        else -> RightBlockFactory()
    }
}


