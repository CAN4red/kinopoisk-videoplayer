package com.example.videoplayerassignment.presentation.film_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videoplayerassignment.common.Resource
import com.example.videoplayerassignment.domain.model.FilmItem
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
        Log.i("State Value", state.value.toString())
        if (state.value.isLoading) return

        viewModelScope.launch {
            getNewFilmPagesUseCase(
                currentFilms = _state.value.films,
                filmListInfo = _state.value.filmListInfo
            ).collect { resource ->
                Log.i("Collect Resource", if (resource is Resource.Success) resource.data.size.toString() else "Not Success")
                updateFilmState(resource)
            }
        }
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            getFilmListInfoUseCase().collect { resource ->
                Log.i("Load Init Data Result", resource.toString())
                when (resource) {
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                filmListInfo = resource.data,
                                isLoading = false,
                            )
                        }
                        loadMoreFilms()
                    }

                    is Resource.Error -> updateErrorState(resource.message)
                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    private fun updateFilmState(resource: Resource<List<FilmItem>>) {
        Log.i("Success Update", if (resource is Resource.Success) resource.data.size.toString() else "Not Success")
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