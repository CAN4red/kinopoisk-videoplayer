package com.example.videoplayerassignment.presentation.film_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videoplayerassignment.common.Resource
import com.example.videoplayerassignment.data.remote.dto.Film
import com.example.videoplayerassignment.domain.use_case.GetFilmListInfoUseCase
import com.example.videoplayerassignment.domain.use_case.GetNewFilmPagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmListViewModel @Inject constructor(
    private val getNewFilmPagesUseCase: GetNewFilmPagesUseCase,
    private val getFilmListInfoUseCase: GetFilmListInfoUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(FilmListState())
    val state: StateFlow<FilmListState> = _state.asStateFlow()

    init {
        loadInitialData()
    }

    fun loadMoreFilms() {
        if (state.value.isLoading) return

        viewModelScope.launch {
            getNewFilmPagesUseCase(
                currentFilms = _state.value.films,
                filmListInfo = _state.value.filmListInfo
            ).collect { resource ->
                updateFilmState(resource)
            }
        }
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            getFilmListInfoUseCase().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _state.update { it.copy(filmListInfo = resource.data) }
                        loadMoreFilms()
                    }
                    is Resource.Error -> updateErrorState(resource.message)
                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    private fun updateFilmState(resource: Resource<List<Film>>) {
        _state.update { current ->
            when (resource) {
                is Resource.Success -> current.copy(
                    films = resource.data,
                    isLoading = false,
                    error = ""
                )
                is Resource.Error -> current.copy(
                    isLoading = false,
                    error = resource.message,
                    films = resource.data ?: current.films
                )
                is Resource.Loading -> current.copy(
                    films = resource.data ?: current.films
                )
            }
        }
    }

    private fun updateErrorState(message: String?) {
        _state.update {
            it.copy(
                isLoading = false,
                error = message ?: "Unknown error"
            )
        }
    }
}