package com.matin.turbocar.ui.game.logic

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn

class TimerLogic(private val scope: CoroutineScope) {
    private val _gameOver = MutableStateFlow(false)
    private val gameOver = _gameOver.asStateFlow()

    fun time() = flow {
        while (true) {
            delay(40)
            if (gameOver.value) {
                break
            }
            emit(1)
        }
    }.shareIn(scope, SharingStarted.Eagerly)

    fun endGame() {
        _gameOver.value = true
    }

    fun restartGame() {
        _gameOver.value = false
    }
}