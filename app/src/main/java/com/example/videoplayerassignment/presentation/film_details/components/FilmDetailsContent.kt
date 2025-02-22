package com.example.videoplayerassignment.presentation.film_details.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.media3.common.Player
import com.example.videoplayerassignment.domain.model.FilmDetails
import com.example.videoplayerassignment.presentation.film_details.FilmDetailsUtils
import com.example.videoplayerassignment.presentation.film_details.MediaEvent

@Composable
fun FilmDetailsContent(
    player: Player,
    onMediaEvent: (MediaEvent) -> Unit,
    filmDetails: FilmDetails?,
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

    if (FilmDetailsUtils.isLandscape()) {
        FilmDetailsUtils.HideSystemUIInLandscape()
    }

    LazyColumn {
        item {
            PlayerViewContainer(
                player = player,
                lifecycleEvent = lifecycleEvent,
            )
        }

        item {
            MediaControlBar(
                isPlaying = player.isPlaying,
                onMediaEvent = onMediaEvent,
            )
        }

        item {
            filmDetails?.let { FilmDetailsInfo(filmDetails = filmDetails) }
        }
    }
}
