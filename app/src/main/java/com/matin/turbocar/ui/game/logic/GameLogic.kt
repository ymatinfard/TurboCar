package com.matin.turbocar.ui.game.logic

interface SyncLogic {
    fun onUpdate(time: Int)
}

interface RestartLogic {
    fun onRestart()
}