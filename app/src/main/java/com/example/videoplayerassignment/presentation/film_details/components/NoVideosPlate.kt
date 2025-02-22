package com.example.videoplayerassignment.presentation.film_details.components

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
            painter = painterResource(R.drawable.sad),
            contentDescription = "no videos",
            modifier = Modifier.size(128.dp)
        )
        Button(
            onClick = navigateBack
        ) {
            Text("Go Back")
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
