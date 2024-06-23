package com.matin.turbocar.ui.game.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.matin.turbocar.ui.game.logic.BlockAdderLogic
import com.matin.turbocar.ui.game.logic.BlockLogic
import com.matin.turbocar.ui.game.logic.LogicManager
import com.matin.turbocar.ui.game.logic.PlayerCollisionLogic
import com.matin.turbocar.ui.game.logic.PlayerLogic
import com.matin.turbocar.ui.game.logic.TimerLogic
import com.matin.turbocar.ui.game.model.BlockFactoryProvider
import com.matin.turbocar.ui.game.model.Size
import com.matin.turbocar.ui.game.model.ViewPort
import kotlinx.coroutines.CoroutineScope

class GameDi(viewPort: ViewPort, scope: CoroutineScope) {

    val timerLogic = TimerLogic(scope)
    val blockLogic = BlockLogic(viewPort, 550f)
    val blockAdderLogic = BlockAdderLogic(scope, blockLogic, viewPort, BlockFactoryProvider)
    val playerLogic = PlayerLogic(scope, viewPort, Size(60f, 100f))
    val playerCollisionLogic = PlayerCollisionLogic(playerLogic, blockLogic)
    val logics = listOf(blockLogic, playerCollisionLogic)
    val logicManager = LogicManager(scope, timerLogic, logics)

    init {
        blockAdderLogic.startBlockAdding()
        logicManager.manage()
    }

    companion object {
        @Composable
        fun rememberGameDi(viewPort: ViewPort, scope: CoroutineScope) = remember {
            GameDi(viewPort, scope)
        }
    }
}