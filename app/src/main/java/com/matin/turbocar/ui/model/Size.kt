package com.matin.turbocar.ui.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

data class Size(val width: Float, val height: Float)

fun Size.toDpSize() = DpSize(width.dp, height.dp)

fun Size.toComposeDpSize() = androidx.compose.ui.geometry.Size(width, height)

@Composable
fun DpSize.toSize(): androidx.compose.ui.geometry.Size {
    return androidx.compose.ui.geometry.Size(this.width.toPx(), this.height.toPx())
}

@Composable
fun Dp.toPx(): Float = with(LocalDensity.current) { this@toPx.toPx() }
