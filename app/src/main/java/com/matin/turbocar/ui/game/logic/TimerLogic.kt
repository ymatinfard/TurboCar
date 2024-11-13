package com.matin.turbocar.ui.game.logic

import android.util.Log
import com.matin.turbocar.ui.game.utils.gameSpeed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch

class TimerLogic(private val scope: CoroutineScope) {
    var gameOver = MutableStateFlow(true)
        private set

    var restart = MutableSharedFlow<Unit>()
        private set

    fun time() = flow {
        while (true) {
            delay(60 - gameSpeed.toLong())
            if (gameOver.value.not()) {
                emit(1)
            }
        }
    }.shareIn(scope, SharingStarted.Eagerly)

    fun endGame() {
        gameOver.value = true
    }

    fun restartGame() {
        scope.launch {
            restart.emit(Unit)
        }
        gameOver.value = false
    }
}