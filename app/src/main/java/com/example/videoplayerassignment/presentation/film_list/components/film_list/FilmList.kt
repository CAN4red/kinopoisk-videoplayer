package com.example.videoplayerassignment.presentation.film_list.components.film_list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.videoplayerassignment.domain.model.FilmItem

@Composable
fun FilmList(
    films: List<FilmItem>,
    onFilmItemClick: (Int) -> Unit,
    onUpdateFilms: () -> Unit,
    loadMoreFilms: () -> Unit,
    modifier: Modifier = Modifier,
) {

    SwipeRefreshWrapper(
        onRefresh = onUpdateFilms,
        modifier = modifier
    ) {
        FilmListContent(
            films = films,
            onFilmItemClick = onFilmItemClick,
            loadMoreFilms = loadMoreFilms,
            modifier = Modifier.fillMaxSize()
        )
    }
}
