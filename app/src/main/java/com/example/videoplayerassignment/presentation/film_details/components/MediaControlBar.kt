package com.example.videoplayerassignment.presentation.film_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.videoplayerassignment.presentation.film_details.MediaEvent

@Composable
fun MediaControlBar(
    isPlaying: Boolean,
    onMediaEvent: (MediaEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
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
                    contentDescription = "Seek Back"
                )
            }

            val playPauseEvent = if (isPlaying) MediaEvent.Pause else MediaEvent.Play
            val playPauseIcon = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow
            IconButton(onClick = { onMediaEvent(playPauseEvent) }) {
                Icon(
                    imageVector = playPauseIcon,
                    contentDescription = "Play Pause"
                )
            }

            IconButton(
                onClick = { onMediaEvent(MediaEvent.SeekForward) }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                    contentDescription = "Seek Back"
                )
            }
        }
        HorizontalDivider(thickness = 2.dp, color = Color.Gray.copy(alpha = 0.6f))
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