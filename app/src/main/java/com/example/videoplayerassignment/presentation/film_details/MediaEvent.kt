package com.example.videoplayerassignment.presentation.film_details

sealed interface MediaEvent {
    data object Play : MediaEvent
    data object Pause : MediaEvent
    data object SeekForward : MediaEvent
    data object SeekBack : MediaEvent
}