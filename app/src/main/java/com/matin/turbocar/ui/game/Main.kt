package com.matin.turbocar.ui.game

import Background
import android.media.MediaPlayer
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.matin.turbocar.R
import com.matin.turbocar.ui.game.component.Block
import com.matin.turbocar.ui.game.component.GameMenu
import com.matin.turbocar.ui.game.component.Player
import com.matin.turbocar.ui.game.component.ext.toPx
import com.matin.turbocar.ui.game.di.GameDi
import com.matin.turbocar.ui.game.engine.gameCoroutineScope
import com.matin.turbocar.ui.game.model.ViewPort

@Composable
fun Main(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val mediaPlayer = remember {
        MediaPlayer.create(context, R.raw.racing).apply {
            isLooping = true
            start()
        }
    }

    BoxWithConstraints(modifier = modifier) {
        val viewPort = ViewPort(maxWidth.toPx(), maxHeight.toPx())
        val gameDi = GameDi.rememberGameDi(viewPort = viewPort, scope = gameCoroutineScope())
        val player = gameDi.playerLogic.player.collectAsState()
        val blocksPosition = gameDi.blockLogic.blocks.collectAsState()
        val isGameOver by gameDi.timerLogic.gameOver.collectAsState()
        val isCollisionHappened = gameDi.playerCollisionLogic.collisionHappened.collectAsState()
        var isFirstTime by remember { mutableStateOf(true) }

        Background(isGameOver)
        Block(modifier, blocksPosition)
        Player(
            modifier,
            viewPort,
            gameDi.playerLogic,
            player,
            isCollisionHappened,
        )

        if (isGameOver) {
            val menuTitle = if (isFirstTime) "Start" else "Restart"
            GameMenu(modifier, title = menuTitle, timerLogic = gameDi.timerLogic)
            isFirstTime = false
            mediaPlayer.pause()
        } else {
            mediaPlayer.start()
        }

        DisposableEffect(Unit) {
            onDispose { mediaPlayer.release() }
        }
    }
}
