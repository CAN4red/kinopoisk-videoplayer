package com.example.videoplayerassignment.features.film_details.presentation

sealed interface MediaEvent {
    data object Play : MediaEvent
    data object Pause : MediaEvent
    data object SeekForward : MediaEvent
    data object SeekBack : MediaEvent
}