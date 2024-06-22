package com.matin.turbocar.ui.engin

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

fun gameCoroutineScope() = CoroutineScope(Dispatchers.IO)