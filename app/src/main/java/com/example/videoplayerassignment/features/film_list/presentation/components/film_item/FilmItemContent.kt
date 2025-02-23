package com.example.videoplayerassignment.features.film_list.presentation.components.film_item

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.videoplayerassignment.features.film_list.domain.model.FilmItem
import com.example.videoplayerassignment.features.film_list.presentation.components.FilmListUtils

@Composable
fun FilmItemContent(
    film: FilmItem,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        FilmContentRow(
            imageUrl = film.posterUrlPreview,
            title = film.name,
            subtitle = FilmListUtils.composeSubtitles(
                genres = film.genres,
                year = film.year,
                countries = film.countries
            )
        )
    }
}

@Composable
private fun FilmContentRow(
    imageUrl: String,
    title: String,
    subtitle: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        FilmThumbnail(
            imageUrl = imageUrl,
            modifier = Modifier.size(width = 90.dp, height = 120.dp)
        )

        FilmTextInfo(
            title = title,
            subtitle = subtitle,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview
@Composable
private fun FilmListItemContentPreview() {
    val film = FilmItem(
        id = 1,
        name = "Avengers",
        year = "2012",
        countries = listOf("USA", "Russia"),
        genres = listOf("Superheroes"),
        posterUrlPreview = "https://kinopoiskapiunofficial.tech/images/posters/kp_small/301.jpg",
    )
    FilmItemContent(film = film)
}