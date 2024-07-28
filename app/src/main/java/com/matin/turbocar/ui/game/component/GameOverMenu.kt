package com.matin.turbocar.ui.game.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matin.turbocar.ui.game.engine.gameCoroutineScope
import com.matin.turbocar.ui.game.logic.TimerLogic

@Preview
@Composable
fun GameOverMenu(
    modifier: Modifier = Modifier,
    timerLogic: TimerLogic = TimerLogic(gameCoroutineScope()),
    exitGameClick: () -> Unit = {},
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        ElevatedCard(
            modifier = Modifier.size(width = 300.dp, height = 200.dp),
        ) {
            Column(
                modifier = modifier.padding(8.dp).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Game Over!", fontSize = 26.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(22.dp))
                TurboButton(text = "Restart",
                    onClick = { timerLogic.restartGame() })
                TurboButton(text = "Exit") {
                    exitGameClick()
                }
            }
        }
    }
}
