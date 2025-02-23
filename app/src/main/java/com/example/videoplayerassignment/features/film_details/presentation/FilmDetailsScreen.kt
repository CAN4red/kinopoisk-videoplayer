package com.example.videoplayerassignment.features.film_details.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.videoplayerassignment.core.presentation.screens.ErrorScreenContent
import com.example.videoplayerassignment.core.presentation.screens.LoadingScreenContent
import com.example.videoplayerassignment.features.film_details.presentation.components.film_details.FilmDetailsContent

@Composable
fun FilmDetailsScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: FilmDetailsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Box(modifier = modifier) {
        when {
            state.isLoading -> {
                LoadingScreenContent()
            }

            state.error != null -> {
                ErrorScreenContent(
                    message = state.error!!,
                    onRetry = viewModel::loadFilmDetails,
                )
            }

            else -> {
                FilmDetailsContent(
                    player = viewModel.player,
                    onMediaEvent = viewModel::onMediaEvent,
                    filmDetails = state.filmDetails,
                    hasVideo = state.hasVideo,
                    navigateBack = navController::navigateUp
                )
            }
        }
        IconButton(
            onClick = navController::navigateUp,
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back Button",
            )
        }
    }
}
