package com.example.videoplayerassignment.presentation.film_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videoplayerassignment.common.Resource
import com.example.videoplayerassignment.domain.model.FilmItem
import com.example.videoplayerassignment.domain.use_case.GetCachedFilmsUseCase
import com.example.videoplayerassignment.domain.use_case.GetNewFilmsUseCase
import com.example.videoplayerassignment.domain.use_case.UpdateFilmListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmListViewModel @Inject constructor(
    private val getCachedFilmsUseCase: GetCachedFilmsUseCase,
    private val updateFilmListUseCase: UpdateFilmListUseCase,
    private val getNewFilmsUseCase: GetNewFilmsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(FilmListState())
    val state: StateFlow<FilmListState> = _state.asStateFlow()

    init {
        loadCacheData()
        updateFilms()
    }

    fun loadMoreFilms() {
        if (_state.value.isLoading) return

        viewModelScope.launch {
            getNewFilmsUseCase().collect { resource ->
                when (resource) {
                    is Resource.Loading -> _state.update { currentState ->
                        currentState.copy(isLoading = true)
                    }

                    is Resource.Error -> _state.update { currentState ->
                        currentState.copy(error = resource.message, isLoading = false)
                    }

                    is Resource.Success -> _state.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            films = currentState.films + resource.data,
                            error = null
                        )
                    }
                }
            }
        }
    }

    fun updateFilms() {
        viewModelScope.launch {
            updateFilmListUseCase().collect { resource ->
                _state.value = resource.processResource()
            }
        }
    }

    private fun loadCacheData() {
        viewModelScope.launch {
            getCachedFilmsUseCase().collect { resource ->
                _state.value = resource.processResource()
            }
        }
    }

    fun clearError() {
        _state.update { currentState ->
            currentState.copy(error = null)
        }
    }

    private fun Resource<List<FilmItem>>.processResource(): FilmListState {
        return when (this) {
            is Resource.Loading -> _state.value.copy(isLoading = true)
            is Resource.Error -> _state.value.copy(error = message, isLoading = false)
            is Resource.Success -> FilmListState(films = data)
        }
    }
}
