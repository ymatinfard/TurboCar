package com.matin.turbocar.ui.game.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import com.matin.turbocar.ui.game.component.ext.toComposeDpSize

data class Player(val x: Float, val y: Float, val offsetX: Float, val size: Size = Size(60f, 60f)) {
    val rect = Rect(
        offset = Offset(x + offsetX - (size.width / 2), y - size.height),
        size = size.toComposeDpSize()
    )
}

