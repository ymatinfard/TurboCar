package com.matin.turbocar.ui.game

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.matin.turbocar.ui.game.component.Background
import com.matin.turbocar.ui.game.component.Block
import com.matin.turbocar.ui.game.component.GameMenu
import com.matin.turbocar.ui.game.component.Player
import com.matin.turbocar.ui.game.component.ext.toPx
import com.matin.turbocar.ui.game.di.GameDi
import com.matin.turbocar.ui.game.engine.gameCoroutineScope
import com.matin.turbocar.ui.game.model.ViewPort

@Composable
fun Main(modifier: Modifier = Modifier) {
    BoxWithConstraints(modifier = modifier) {
        val viewPort = ViewPort(maxWidth.toPx(), maxHeight.toPx())
        val gameDi = GameDi.rememberGameDi(viewPort = viewPort, scope = gameCoroutineScope())
        var isFirstTime by remember {
            mutableStateOf(true)
        }
        val player = gameDi.playerLogic.player.collectAsState()
        val blocksPosition = gameDi.blockLogic.blocks.collectAsState()
        val isGameOver = gameDi.timerLogic.gameOver.collectAsState()
        val isCollisionHappened = gameDi.playerCollisionLogic.collisionHappened.collectAsState()

        Background(isGameOver)
        Block(modifier, blocksPosition)
        Player(
            modifier,
            viewPort,
            gameDi.playerLogic,
            player,
            isCollisionHappened,
        )

        if (isGameOver.value) {
            val menuTitle = if (isFirstTime) "Start" else "Restart"
            GameMenu(modifier, title = menuTitle, timerLogic = gameDi.timerLogic)
            isFirstTime = false
        }
    }
}
