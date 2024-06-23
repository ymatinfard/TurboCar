package com.matin.turbocar.ui.game.component

import android.graphics.BitmapShader
import android.graphics.Shader
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.imageResource
import com.matin.turbocar.R
import kotlinx.coroutines.delay

@Composable
fun Background() {
    Box {
        var scrollY by remember {
            mutableFloatStateOf(0f)
        }

        LaunchedEffect(key1 = Unit) {
            while (true) {
                delay(1)
                scrollY += 4
            }
        }

        val paint = Paint().asFrameworkPaint().apply {
            shader = BitmapShader(
                ImageBitmap.imageResource(id = R.drawable.road).asAndroidBitmap(),
                Shader.TileMode.REPEAT,
                Shader.TileMode.MIRROR
            )
        }
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawIntoCanvas {
                it.translate(0f, scrollY)
                it.nativeCanvas.drawPaint(paint)
                it.translate(0f, 0f)
            }
        }
    }
}