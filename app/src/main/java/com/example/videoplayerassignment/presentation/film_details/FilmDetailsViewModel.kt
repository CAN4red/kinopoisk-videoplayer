package com.example.videoplayerassignment.presentation.film_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videoplayerassignment.common.Constants
import com.example.videoplayerassignment.common.Resource
import com.example.videoplayerassignment.domain.use_case.GetFilmDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmDetailsViewModel @Inject constructor(
    private val getFilmDetailsUseCase: GetFilmDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(FilmDetailsState())
    val state: StateFlow<FilmDetailsState> get() = _state.asStateFlow()

    init {
        savedStateHandle.get<String>(Constants.PARAM_FILM_ID)?.let { filmId ->
            getFilmDetails(filmId.toInt())
        }
    }

    private fun getFilmDetails(filmId: Int) {
        viewModelScope.launch {
            getFilmDetailsUseCase(filmId).collect { resource ->
                _state.value = when (resource) {
                    is Resource.Loading -> FilmDetailsState(isLoading = true)
                    is Resource.Error -> FilmDetailsState(error = resource.message)
                    is Resource.Success -> FilmDetailsState(filmDetails = resource.data)
                }
            }
        }
    }
}