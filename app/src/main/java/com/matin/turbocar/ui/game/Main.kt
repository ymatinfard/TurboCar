package com.matin.turbocar.ui.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.matin.turbocar.ui.game.component.Background
import com.matin.turbocar.ui.game.component.Block
import com.matin.turbocar.ui.game.component.Player
import com.matin.turbocar.ui.game.component.ext.toPx
import com.matin.turbocar.ui.game.di.GameDi
import com.matin.turbocar.ui.game.engine.gameCoroutineScope
import com.matin.turbocar.ui.game.model.ViewPort

@Composable
fun Main(modifier: Modifier = Modifier) {
    BoxWithConstraints(modifier = Modifier.background(color = Color.Cyan)) {
        val viewPort = ViewPort(maxWidth.toPx(), maxHeight.toPx())
        val gameDi = GameDi.rememberGameDi(viewPort = viewPort, scope = gameCoroutineScope())

        val playerPosition = gameDi.playerLogic.player.collectAsState()
        val blocksPosition = gameDi.blockLogic.blocks.collectAsState()
        val isCollisionHappened = gameDi.playerCollisionLogic.collisionHappened.collectAsState()

        Background()
        Block(modifier, blocksPosition)
        Player(
            modifier,
            viewPort,
            gameDi.playerLogic,
            playerPosition,
            isCollisionHappened,
        )
    }
}

