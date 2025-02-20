package com.example.videoplayerassignment.presentation.film_details

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FilmDetailsState(
    modifier: Modifier = Modifier,
    viewModel: FilmDetailsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    state.filmDetails?.let { filmDetails ->
        LazyColumn(modifier = modifier) {
            item {
                Text(text = filmDetails.name)
            }
        }
    }
}