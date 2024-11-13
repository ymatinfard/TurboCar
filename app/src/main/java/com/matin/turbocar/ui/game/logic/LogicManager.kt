package com.matin.turbocar.ui.game.logic

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LogicManager(
    private val scope: CoroutineScope,
    private val timer: TimerLogic,
    private val syncLogics: List<SyncLogic>,
    private val restartLogics: List<RestartLogic>,
) {
    fun manage() {
        scope.launch {
            timer.time().collectLatest { time ->
                syncLogics.forEach {
                    it.onUpdate(time)
                }
            }
        }

        scope.launch {
            timer.restart.collectLatest {
                restartLogics.forEach {
                    it.onRestart()
                }
            }
        }
    }
}