package com.matin.turbocar.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.matin.turbocar.Block

@Composable
fun Block(modifier: Modifier, blockPosition: State<Block>, direction: Direction) {
    println(blockPosition.value)
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = if (direction == Direction.RIGHT) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier
                .offset { IntOffset( x = 0, y = blockPosition.value.y) }
                .size(width = 90.dp, height = 30.dp)
                .background(color = Color.Black)
        )
    }
}