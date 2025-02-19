package com.example.videoplayerassignment.presentation.film_list

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.videoplayerassignment.presentation.film_list.components.FilmList

@Composable
fun FilmListScreen(
    modifier: Modifier = Modifier,
    viewModel: FilmListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Log.i("Film List Update", state.films.size.toString())

    FilmList(
        films = state.films,
        onFilmItemClick = { },
        loadMoreFilms = viewModel::loadMoreFilms,
        modifier = modifier.fillMaxSize()
    )
}