package com.example.videoplayerassignment.presentation.film_list.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.videoplayerassignment.domain.model.FilmItem
import com.example.videoplayerassignment.presentation.film_list.components.film_item.FilmItem
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun FilmList(
    films: List<FilmItem>,
    onFilmItemClick: (Int) -> Unit,
    onUpdateFilms: () -> Unit,
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

    var refreshing by remember { mutableStateOf(false) }
    LaunchedEffect(refreshing) {
        if (refreshing) {
            onUpdateFilms()
            refreshing = false
        }
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = refreshing),
        onRefresh = { refreshing = true }
    ) {
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
}