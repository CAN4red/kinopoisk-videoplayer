package com.example.videoplayerassignment.features.film_list.presentation.components.film_list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.videoplayerassignment.features.film_list.domain.model.FilmItem
import com.example.videoplayerassignment.features.film_list.presentation.components.film_item.FilmItem

@Composable
fun FilmListContent(
    films: List<FilmItem>,
    onFilmItemClick: (Int) -> Unit,
    loadMoreFilms: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()

    val shouldLoadMore by remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val totalItems = layoutInfo.totalItemsCount
            val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

            lastVisibleItem >= totalItems - 10
        }
    }

    LaunchedEffect(shouldLoadMore) {
        if (shouldLoadMore) {
            loadMoreFilms()
        }
    }

    LazyColumn(
        state = listState,
        modifier = modifier
    ) {
        items(
            items = films,
            key = { film -> film.id }
        ) { item ->
            FilmItem(
                film = item,
                onClick = onFilmItemClick,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
