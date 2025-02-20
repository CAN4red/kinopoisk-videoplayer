package com.example.videoplayerassignment.data.mappers

import com.example.videoplayerassignment.data.remote.dto.FilmDetailsDto
import com.example.videoplayerassignment.data.remote.dto.FilmVideosDto
import com.example.videoplayerassignment.domain.model.FilmDetails

object FilmDetailsMapper {

    fun dtoToDomain(detailsDto: FilmDetailsDto, videosDto: FilmVideosDto) = FilmDetails(
        id = detailsDto.id,
        name = detailsDto.name,
        year = detailsDto.year?.toString() ?: "",
        description = detailsDto.description,
        countries = detailsDto.countries.map { it.name },
        genres = detailsDto.genres.map { it.name },
        videoUrl = videosDto.videos.first().url
    )
}