package com.example.videoplayerassignment.presentation.film_list.components.film_item

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.videoplayerassignment.domain.model.FilmItem

@Composable
fun FilmItemContent(
    film: FilmItem,
    modifier: Modifier = Modifier,
) {
    val cardAspectRatio = 3f

    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(cardAspectRatio),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        FilmContentRow(
            imageUrl = film.posterUrlPreview,
            title = film.name,
            year = film.year
        )
    }
}

@Composable
private fun FilmContentRow(
    imageUrl: String,
    title: String,
    year: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxSize()
    ) {
        FilmThumbnail(
            imageUrl = imageUrl,
            modifier = Modifier.size(width = 90.dp, height = 120.dp)
        )

        FilmTextInfo(
            title = title,
            year = year,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview
@Composable
fun FilmListItemContentPreview() {
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