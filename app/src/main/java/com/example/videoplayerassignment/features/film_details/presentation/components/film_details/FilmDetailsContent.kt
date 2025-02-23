package com.example.videoplayerassignment.features.film_details.presentation.components.film_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.media3.common.Player
import com.example.videoplayerassignment.features.film_details.domain.model.FilmDetails
import com.example.videoplayerassignment.features.film_details.presentation.MediaEvent
import com.example.videoplayerassignment.features.film_details.presentation.components.FilmDetailsUtils
import com.example.videoplayerassignment.features.film_details.presentation.components.player.MediaControlBar
import com.example.videoplayerassignment.features.film_details.presentation.components.player.NoVideosPlate
import com.example.videoplayerassignment.features.film_details.presentation.components.player.PlayerViewContainer

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
