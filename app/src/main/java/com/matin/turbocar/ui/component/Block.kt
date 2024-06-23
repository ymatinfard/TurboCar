package com.matin.turbocar.ui.component

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
import com.matin.turbocar.ui.Direction
import com.matin.turbocar.ui.model.Block
import com.matin.turbocar.ui.toDp

@Composable
fun Block(modifier: Modifier, blocks: State<List<Block>>) {
    blocks.value.forEach { block ->
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = when (block.direction) {
                Direction.LEFT -> Arrangement.Start
                Direction.CENTER -> Arrangement.Center
                Direction.RIGHT -> Arrangement.End
            },
        ) {
            Box(
                modifier
                    .offset { IntOffset(x = 0, y = block.y.toInt()) }
                    .size(
                        width = (block.endX - block.startX).toDp(),
                        height = 40.dp
                    )
                    .background(color = Color.Black)
            )
        }
    }
}