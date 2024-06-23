package com.matin.turbocar.ui.game.logic

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LogicManager(
    private val scope: CoroutineScope,
    private val timer: TimerLogic,
    private val gameLogics: List<GameLogic>
) {
    fun manage() {
        scope.launch {
            timer.time().collect { time ->
                gameLogics.forEach {
                    it.onUpdate(time)
                }
            }
        }
    }
}