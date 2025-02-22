package com.example.videoplayerassignment.presentation.film_details.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.media3.common.Player
import androidx.media3.ui.PlayerView
import com.example.videoplayerassignment.presentation.film_details.FilmDetailsUtils

@Composable
fun PlayerViewContainer(
    player: Player,
    lifecycleEvent: Lifecycle.Event,
    modifier: Modifier = Modifier,
) {
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
