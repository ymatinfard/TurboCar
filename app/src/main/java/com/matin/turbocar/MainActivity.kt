package com.matin.turbocar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.matin.turbocar.ui.game.Game
import com.matin.turbocar.ui.home.Home
import com.matin.turbocar.ui.theme.TurbocarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            TurbocarTheme {
                var currentScreen by rememberSaveable { mutableStateOf(Screen.Home) }
                Box(modifier = Modifier.fillMaxSize()) {
                    when (currentScreen) {
                        Screen.Home -> Home {
                            currentScreen = it
                        }

                        Screen.Game -> Game {
                            currentScreen = it
                        }

                        else -> {}
                    }
                }
            }
        }
    }
}

enum class Screen {
    Home, Game, Settings, Help
}

@PreviewScreenSizes
@Composable
fun MainPreview() {
    TurbocarTheme {
        Game()
    }
}