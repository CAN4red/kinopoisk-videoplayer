package com.example.videoplayerassignment.presentation.film_list.components.film_item

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.example.videoplayerassignment.R

@Composable
internal fun FilmThumbnail(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .transformations(RoundedCornersTransformation(16f))
            .build(),
        placeholder = painterResource(id = R.drawable.loading_icon),
        error = painterResource(id = R.drawable.error_icon),
        contentDescription = "Film Thumbnail",
        modifier = modifier.padding(12.dp)
    )
}