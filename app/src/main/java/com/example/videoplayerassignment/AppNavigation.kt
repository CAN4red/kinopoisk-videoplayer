package com.example.videoplayerassignment

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
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
        composable(
            route = NavRoutes.FILM_DETAILS + "/{filmId}",
            enterTransition = ::slideInToTop,
            exitTransition = ::slideOutToDown
        ) {
            FilmDetailsScreen(navController = navController)
        }
    }
}

private fun slideInToTop(scope: AnimatedContentTransitionScope<NavBackStackEntry>): EnterTransition {
    return scope.slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Up,
        animationSpec = tween(500)
    )
}

private fun slideOutToDown(scope: AnimatedContentTransitionScope<NavBackStackEntry>): ExitTransition {
    return scope.slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Down,
        animationSpec = tween(500)
    )
}
