package com.example.videoplayerassignment.features.film_details.data.remote.api

import com.example.videoplayerassignment.features.film_details.data.remote.dto.FilmDetailsDto
import com.example.videoplayerassignment.features.film_details.data.remote.dto.FilmVideosDto
import retrofit2.http.GET
import retrofit2.http.Path

interface FilmDetailsApi {

    @GET("api/v2.2/films/{id}")
    suspend fun getFilmDetails(@Path("id") id: Int): FilmDetailsDto

    @GET("api/v2.2/films/{id}/videos")
    suspend fun getFilmVideos(@Path("id") id: Int): FilmVideosDto
}