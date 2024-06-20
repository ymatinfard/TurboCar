package com.matin.turbocar.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.matin.turbocar.Block
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@Composable
fun Main(modifier: Modifier = Modifier) {
    val configuration = LocalConfiguration.current
    val screenWidthPx = dpToPx(configuration.screenWidthDp.dp)
    val screenHeightPx = dpToPx(configuration.screenHeightDp.dp)
    val coroutineScope = remember { CoroutineScope(Dispatchers.IO) }

    val playerLogic = remember { PlayerLogic(coroutineScope, screenWidthPx) }
    val blockLogic = remember { BlockLogic(coroutineScope, screenHeightPx) }
    val playerPosition = playerLogic.playerPosition.collectAsState()
    val blockPosition = blockLogic.blockPosition.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Block(modifier, blockPosition, Direction.LEFT)
        Block(modifier, blockPosition, Direction.RIGHT)
        Player(modifier, screenWidthPx, playerLogic, playerPosition)
    }
}

@Composable
fun Block(modifier: Modifier, blockPosition: State<Block>, direction: Direction) {
    println(blockPosition.value)
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = if (direction == Direction.RIGHT) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier
                .offset { IntOffset(y = blockPosition.value.y, x = 0) }
                .size(width = 90.dp, height = 30.dp)
                .background(color = Color.Black)
        )
    }

}

@Composable
private fun Player(
    modifier: Modifier,
    screenWidthPx: Float,
    playerLogic: PlayerLogic,
    playerPosition: State<Player>,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    val direction =
                        if (offset.x < (screenWidthPx / 2)) Direction.LEFT else Direction.RIGHT
                    playerLogic.move(direction)
                }
            },
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .offset { IntOffset(x = playerPosition.value.x, y = 0) }
                .padding(bottom = 20.dp)
                .size(40.dp)
                .background(color = Color.Red)
        )
    }
}

enum class Direction {
    LEFT,
    RIGHT
}

@Composable
fun dpToPx(dp: Dp): Float {
    val density = LocalDensity.current
    return with(density) { dp.toPx() }
}

