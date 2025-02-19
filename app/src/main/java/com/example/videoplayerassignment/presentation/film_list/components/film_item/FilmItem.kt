package com.example.videoplayerassignment.presentation.film_list.components.film_item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.videoplayerassignment.data.remote.dto.Film

@Composable
fun FilmItem(
    film: Film,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .clip(CardDefaults.shape)
            .clickable { onClick(film.id) }
    ) {
        FilmItemContent(film = film)
    }
}