package com.matin.turbocar.ui.logic

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class TimerLogic {
    val time = flow {
        while (true) {
            delay(100)
            emit(1)
        }
    }
}