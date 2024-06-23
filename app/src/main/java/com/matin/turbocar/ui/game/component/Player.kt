package com.matin.turbocar.ui.game.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.matin.turbocar.R
import com.matin.turbocar.ui.game.logic.PlayerLogic
import com.matin.turbocar.ui.game.model.Player
import com.matin.turbocar.ui.game.model.ViewPort
import com.matin.turbocar.ui.game.utils.Direction

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
        Image(painter = painterResource(id = R.drawable.car),
            modifier = Modifier
                .size(80.dp, 120.dp)
                .offset { IntOffset(x = player.value.x.toInt(), y = 0) },
            contentDescription = null
        )
    }
}