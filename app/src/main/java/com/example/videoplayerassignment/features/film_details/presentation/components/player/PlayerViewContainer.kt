package com.example.videoplayerassignment.features.film_details.presentation.components.player

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.media3.common.Player
import androidx.media3.ui.PlayerView
import com.example.videoplayerassignment.features.film_details.presentation.components.FilmDetailsUtils

@Composable
fun PlayerViewContainer(
    player: Player,
    modifier: Modifier = Modifier,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var lifecycleEvent by remember { mutableStateOf(Lifecycle.Event.ON_CREATE) }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycleEvent = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    AndroidView(
        factory = { context ->
            PlayerView(context).also { it.player = player }
        },
        update = {
            when (lifecycleEvent) {
                Lifecycle.Event.ON_RESUME -> {
                    it.onResume()
                }

                Lifecycle.Event.ON_PAUSE -> {
                    it.onPause()
                    it.player?.pause()
                }

                Lifecycle.Event.ON_DESTROY -> {
                    it.player?.release()
                }

                else -> Unit
            }
        },
        modifier = modifier.aspectRatio(computeAspectRatio())
    )
}

@Composable
private fun computeAspectRatio(): Float {
    val configuration = LocalConfiguration.current

    if (!FilmDetailsUtils.isLandscape()) {
        return 16 / 9f
    }
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp

    return screenWidth.toFloat() / screenHeight.toFloat()
}
