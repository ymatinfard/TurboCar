package com.matin.turbocar.ui.game.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.matin.turbocar.R
import com.matin.turbocar.ui.game.component.ext.toDp
import com.matin.turbocar.ui.game.model.Block
import com.matin.turbocar.ui.game.utils.Direction

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
            Image(
                painter = painterResource(id = R.drawable.block),
                modifier = modifier
                    .offset { IntOffset(x = 0, y = block.y.toInt()) }
                    .size(
                        width = (block.endX - block.startX).toDp(),
                        height = 40.dp
                    ),
                contentScale = ContentScale.Fit,
                contentDescription = null
            )
        }
    }
}