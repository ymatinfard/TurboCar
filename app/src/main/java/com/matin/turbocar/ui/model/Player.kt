package com.matin.turbocar.ui.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect

data class Player(val x: Float, val y: Float, val offsetX: Float, val size: Size = Size(60f, 60f)) {
    val rect = Rect(
        offset = Offset(x + offsetX - (size.width / 2), y - size.height),
        size = size.toComposeDpSize()
    )
}

