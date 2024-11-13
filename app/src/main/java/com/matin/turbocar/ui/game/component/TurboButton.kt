package com.matin.turbocar.ui.game.component

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TurboButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Button (
        onClick = { onClick() },
        modifier = modifier.width(100.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Text(text = text)
    }
}