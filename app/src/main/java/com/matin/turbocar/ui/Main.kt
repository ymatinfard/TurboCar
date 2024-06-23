package com.matin.turbocar.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.matin.turbocar.ui.component.Block
import com.matin.turbocar.ui.component.Player
import com.matin.turbocar.ui.engin.gameCoroutineScope
import com.matin.turbocar.ui.logic.BlockAdderLogic
import com.matin.turbocar.ui.logic.BlockLogic
import com.matin.turbocar.ui.logic.PlayerCollisionLogic
import com.matin.turbocar.ui.logic.PlayerLogic
import com.matin.turbocar.ui.logic.TimerLogic
import com.matin.turbocar.ui.model.BlockFactoryProvider
import com.matin.turbocar.ui.model.ViewPort
import com.matin.turbocar.ui.model.toPx

@Composable
fun Main(modifier: Modifier = Modifier) {
    BoxWithConstraints(modifier = Modifier.background(color = Color.Cyan)) {
        val viewPort = ViewPort(maxWidth.toPx(), maxHeight.toPx())
        val coroutineScope = remember { gameCoroutineScope() }
        val blockSize = 200.dp.toPx()
        val playerSize = com.matin.turbocar.ui.model.Size(20.dp.toPx(), 20.dp.toPx())

        val playerLogic =
            remember {
                PlayerLogic(
                    coroutineScope,
                    viewPort,
                    playerSize
                )
            }
        val blockLogic =
            remember {
                BlockLogic(
                    coroutineScope,
                    TimerLogic,
                    viewPort,
                    blockSize
                )
            }
        val collisionLogic = remember {
            PlayerCollisionLogic(
                playerLogic,
                blockLogic,
                TimerLogic,
                coroutineScope
            )
        }

        val blockAdderLogic = remember {
            BlockAdderLogic(
                scope = coroutineScope,
                blockLogic = blockLogic,
                viewPort = viewPort,
                BlockFactoryProvider
            )
        }

        val playerPosition = playerLogic.player.collectAsState()
        val blocksPosition = blockLogic.blocks.collectAsState()
        val isCollisionHappened = collisionLogic.collisionHappened.collectAsState()
        blockAdderLogic.startBlockAdding()

        Box(modifier = Modifier.fillMaxSize()) {
            Block(modifier, blocksPosition)
            Player(
                modifier,
                viewPort,
                playerLogic,
                playerPosition,
                isCollisionHappened,
            )
        }
    }
}

enum class Direction(val value: Int) {
    LEFT(-1),
    RIGHT(1),
    CENTER(0)
}

@Composable
fun Float.toDp(): Dp = with(LocalDensity.current) { this@toDp.toDp() }

