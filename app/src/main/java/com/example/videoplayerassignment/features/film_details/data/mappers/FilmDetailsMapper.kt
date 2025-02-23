package com.example.videoplayerassignment.features.film_details.data.mappers

import com.example.videoplayerassignment.features.film_details.data.remote.dto.FilmDetailsDto
import com.example.videoplayerassignment.features.film_details.data.remote.dto.FilmVideoDto
import com.example.videoplayerassignment.features.film_details.data.remote.dto.FilmVideosDto
import com.example.videoplayerassignment.features.film_details.domain.model.FilmDetails
import com.example.videoplayerassignment.features.film_details.domain.model.FilmVideo

object FilmDetailsMapper {

    fun dtoToDomain(detailsDto: FilmDetailsDto, videosDto: FilmVideosDto) = FilmDetails(
        id = detailsDto.id,
        name = detailsDto.name,
        year = detailsDto.year?.toString() ?: "",
        description = detailsDto.description,
        countries = detailsDto.countries.map { it.name },
        genres = detailsDto.genres.map { it.name },
        videos = videosDto.videos
            .filter { it.url.endsWith(".mp4") }
            .map { dtoToDomain(it) }
    )

    private fun dtoToDomain(filmVideoDto: FilmVideoDto) = FilmVideo(
        name = filmVideoDto.name,
        url = filmVideoDto.url
    )
}