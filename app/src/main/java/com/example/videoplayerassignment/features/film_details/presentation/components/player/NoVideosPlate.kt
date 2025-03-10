package com.example.videoplayerassignment.features.film_details.presentation.components.player

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.videoplayerassignment.R

@Composable
fun NoVideosPlate(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(R.drawable.no_videos),
            contentDescription = stringResource(R.string.no_videos),
            modifier = Modifier.size(128.dp)
        )
        Button(
            onClick = navigateBack
        ) {
            Text(stringResource(R.string.go_back))
        }
    }
}

@Preview
@Composable
private fun NoVideosPlatePreview() {
    NoVideosPlate(
        navigateBack = {}
    )
}
