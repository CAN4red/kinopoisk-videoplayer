package com.example.videoplayerassignment.presentation.film_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
    hasVideo: Boolean,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (FilmDetailsUtils.isLandscape()) {
        FilmDetailsUtils.HideSystemUIInLandscape()
    }

    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            item {
                if (hasVideo) {
                    PlayerViewContainer(player = player)
                } else {
                    NoVideosPlate(navigateBack = navigateBack)
                }
            }

            item {
                filmDetails?.let { FilmDetailsInfo(filmDetails = filmDetails) }
            }
        }
        if (hasVideo && !FilmDetailsUtils.isLandscape()) {
            MediaControlBar(
                isPlaying = player.isPlaying,
                onMediaEvent = onMediaEvent,
                modifier = Modifier
            )
        }
    }
}
