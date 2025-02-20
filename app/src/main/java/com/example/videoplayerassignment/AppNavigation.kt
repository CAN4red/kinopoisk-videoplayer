package com.example.videoplayerassignment

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.videoplayerassignment.common.NavRoutes
import com.example.videoplayerassignment.presentation.film_details.FilmDetailsScreen
import com.example.videoplayerassignment.presentation.film_list.FilmListScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.FILM_LIST,
        modifier = modifier
    ) {
        composable(route = NavRoutes.FILM_LIST) {
            FilmListScreen(navController = navController)
        }
        composable(route = NavRoutes.FILM_DETAILS + "/{filmId}") {
            FilmDetailsScreen()
        }
    }
}
