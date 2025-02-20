package com.example.videoplayerassignment.presentation.film_list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.videoplayerassignment.common.NavRoutes
import com.example.videoplayerassignment.presentation.common.snackbar.ErrorSnackbar
import com.example.videoplayerassignment.presentation.film_list.components.film_list.FilmList

@Composable
fun FilmListScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: FilmListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.error) {
        state.error?.let { message ->
            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Short
            )
        }
        viewModel.clearError()
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                ErrorSnackbar(message = data.visuals.message)
            }
        }
    ) { paddingValues ->
        FilmList(
            films = state.films,
            onFilmItemClick = { filmId -> navController.navigate(NavRoutes.FILM_DETAILS + "/${filmId}") },
            onUpdateFilms = viewModel::updateFilms,
            loadMoreFilms = viewModel::loadMoreFilms,
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
        )
    }
}