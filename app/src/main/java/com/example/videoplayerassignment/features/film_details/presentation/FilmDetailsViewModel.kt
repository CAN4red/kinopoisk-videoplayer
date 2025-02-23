package com.example.videoplayerassignment.features.film_details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.videoplayerassignment.core.common.Constants
import com.example.videoplayerassignment.core.common.Resource
import com.example.videoplayerassignment.features.film_details.domain.use_case.GetFilmDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmDetailsViewModel @Inject constructor(
    private val getFilmDetailsUseCase: GetFilmDetailsUseCase,
    private val savedStateHandle: SavedStateHandle,
    val player: ExoPlayer,
) : ViewModel() {

    private val _state = MutableStateFlow(FilmDetailsState())
    val state: StateFlow<FilmDetailsState> = _state.asStateFlow()

    init {
        loadFilmDetails()
    }

    fun loadFilmDetails() {
        savedStateHandle.get<String>(Constants.PARAM_FILM_ID)?.let { filmId ->
            loadFilmDetailsById(filmId.toInt())
        }
    }

    fun onMediaEvent(event: MediaEvent) {
        when (event) {
            MediaEvent.Play -> playVideo()
            MediaEvent.Pause -> player.pause()
            MediaEvent.SeekForward -> player.seekBack()
            MediaEvent.SeekBack -> player.seekForward()
        }
    }

    private fun playVideo() {
        if (_state.value.isVideoStarted) {
            player.play()
        } else {
            _state.value.filmDetails?.let { filmDetails ->
                player.setMediaItem(MediaItem.fromUri(filmDetails.videos.first().url))
            }
        }
    }

    private fun loadFilmDetailsById(filmId: Int) {
        viewModelScope.launch {
            getFilmDetailsUseCase(filmId).collect { result ->
                _state.value = when (result) {
                    is Resource.Success -> FilmDetailsState(filmDetails = result.data)
                    is Resource.Error -> FilmDetailsState(error = result.message)
                    is Resource.Loading -> FilmDetailsState(isLoading = true)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}