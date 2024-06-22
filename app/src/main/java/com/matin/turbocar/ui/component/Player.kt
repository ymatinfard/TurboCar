package com.matin.turbocar.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import com.matin.turbocar.ui.Direction
import com.matin.turbocar.ui.logic.PlayerLogic
import com.matin.turbocar.ui.model.Player
import com.matin.turbocar.ui.model.ViewPort
import com.matin.turbocar.ui.toDp

@Composable
fun Player(
    modifier: Modifier,
    viewPort: ViewPort,
    playerLogic: PlayerLogic,
    player: State<Player>,
    isCollisionHappened: State<Boolean>,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    val direction =
                        if (offset.x < (viewPort.width / 2)) Direction.LEFT else Direction.RIGHT
                    playerLogic.move(direction)
                }
            },
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .offset { IntOffset(x = player.value.x.toInt(), y = 0) }
                .size(width = player.value.size.width.toDp(), height = player.value.size.width.toDp())
                .background(color = if (isCollisionHappened.value) Color.Red else Color.Blue)
        )
    }
}