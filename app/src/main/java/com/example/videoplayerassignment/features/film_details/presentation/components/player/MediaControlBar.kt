package com.example.videoplayerassignment.features.film_details.presentation.components.player

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.videoplayerassignment.R
import com.example.videoplayerassignment.features.film_details.presentation.MediaEvent

@Composable
fun MediaControlBar(
    isPlaying: Boolean,
    onMediaEvent: (MediaEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth(),
    ) {
        IconButton(
            onClick = { onMediaEvent(MediaEvent.SeekBack) }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                contentDescription = stringResource(R.string.seek_back)
            )
        }

        val playPauseEvent = if (isPlaying) MediaEvent.Pause else MediaEvent.Play
        val playPauseIcon = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow
        IconButton(onClick = { onMediaEvent(playPauseEvent) }) {
            Icon(
                imageVector = playPauseIcon,
                contentDescription = stringResource(R.string.play_pause),
                modifier = Modifier.size(64.dp)
            )
        }

        IconButton(
            onClick = { onMediaEvent(MediaEvent.SeekForward) }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = stringResource(R.string.seek_forward)
            )
        }
    }
}

@Preview
@Composable
private fun MediaControlBarPreview() {
    MediaControlBar(
        isPlaying = false,
        onMediaEvent = {},
    )
}