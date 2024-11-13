package com.matin.turbocar.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matin.turbocar.Screen
import com.matin.turbocar.ui.theme.TurbocarTheme

@Composable
fun Home(onMenuClick: (Screen) -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        BubbleBackground(
            modifier = Modifier.fillMaxSize(),
            numberBubbles = 7,
            bubbleColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = .3f)
        )

        BasicInformationalCard(
            modifier = Modifier.size(200.dp),
            borderColor = MaterialTheme.colorScheme.outline,
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    MenuField("Play") { onMenuClick(Screen.Game) }
                    MenuField("Help") { onMenuClick(Screen.Help) }
                    MenuField("Settings") { onMenuClick(Screen.Settings) }
                }
            }
        }
    }
}

@Composable
fun MenuField(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        modifier = Modifier.clickable { onClick() },
        style = MaterialTheme.typography.labelLarge
    )
}

@Composable
fun BasicInformationalCard(
    modifier: Modifier = Modifier,
    borderColor: Color,
    content: @Composable () -> Unit
) {
    val shape = RoundedCornerShape(24.dp)
    Card(
        shape = shape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(.7f)
        ),
        modifier = modifier
            .padding(8.dp),
        border = BorderStroke(2.dp, borderColor)
    ) {
        content()
    }
}

@Preview
@Composable
fun HomePreview() {
    TurbocarTheme {
        Home()
    }
}
