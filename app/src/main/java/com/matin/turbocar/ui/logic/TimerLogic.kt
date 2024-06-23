package com.matin.turbocar.ui.logic

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow

object TimerLogic {
    private val _gameOver = MutableStateFlow(false)
    val gameOver = _gameOver.asStateFlow()

    fun startTimer() = flow {
        while (true) {
            delay(40)
            if (gameOver.value) {
                break
            }
            emit(1)
        }
    }

    fun endGame() {
        _gameOver.value = true
    }

    fun restartGame() {
        _gameOver.value = false
    }
}