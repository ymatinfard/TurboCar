import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import com.matin.turbocar.R
import com.matin.turbocar.ui.game.utils.gameSpeed
import kotlinx.coroutines.delay

@Composable
fun Background(isGameOver: Boolean) {

    Box {
        var scrollY by remember { mutableFloatStateOf(0f) }

        LaunchedEffect(key1 = isGameOver) {
            while (true) {
                if (isGameOver) break
                delay(16) // Approximately 60 FPS for smoother scrolling
                scrollY += gameSpeed
                // Reset scrollY to prevent overflow
                if (scrollY >= Float.MAX_VALUE - 1000) scrollY = 0f
            }
        }

        val context = LocalContext.current
        val imageBitmap = ImageBitmap.imageResource(context.resources, R.drawable.road)

        Canvas(modifier = Modifier.fillMaxSize()) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            // Calculate the scale factor to stretch the image to the width of the canvas
            val scaleFactorX = canvasWidth / imageBitmap.width.toFloat()
            val scaledImageHeight = imageBitmap.height * scaleFactorX

            // Draw images in a way to cover the canvas height
            val currentScrollY = scrollY % scaledImageHeight

            for (y in 0..(canvasHeight / scaledImageHeight).toInt() + 1) {
                val translateY = currentScrollY + y * scaledImageHeight - scaledImageHeight
                drawIntoCanvas { canvas ->
                    canvas.save()
                    canvas.translate(0f, translateY)
                    canvas.scale(scaleFactorX, scaleFactorX)
                    canvas.nativeCanvas.drawBitmap(imageBitmap.asAndroidBitmap(), 0f, 0f, null)
                    canvas.restore()
                }
            }
        }
    }
}

