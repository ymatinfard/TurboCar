package com.matin.turbocar.ui.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect

data class Block(val y: Float, val startX: Float, val endX: Float) {
    val rect = Rect(topLeft = Offset(startX, y), bottomRight = Offset(endX, y))
}


