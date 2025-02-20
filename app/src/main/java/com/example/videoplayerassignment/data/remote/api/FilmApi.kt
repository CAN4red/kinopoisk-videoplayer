package com.example.videoplayerassignment.data.remote.api

import com.example.videoplayerassignment.data.remote.dto.FilmDetailsDto
import com.example.videoplayerassignment.data.remote.dto.FilmListInfoDto
import com.example.videoplayerassignment.data.remote.dto.FilmVideosDto
import com.example.videoplayerassignment.data.remote.dto.FilmsList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmApi {

    @GET("api/v2.2/films/collections?type=$COLLECTION_TYPE&page=1")
    suspend fun getFilmsListInfo(): FilmListInfoDto

    @GET("api/v2.2/films/collections")
    suspend fun getFilmListByPage(
        @Query("page") page: Int,
        @Query("type") collectionType: String = COLLECTION_TYPE
    ): FilmsList

    @GET("api/v2.2/films/{id}")
    suspend fun getFilmDetails(@Path("id") id: Int): FilmDetailsDto

    @GET("api/v2.2/films/{id}/videos")
    suspend fun getFilmVideos(@Path("id") id: Int): FilmVideosDto

    companion object {
        private const val COLLECTION_TYPE = "TOP_POPULAR_MOVIES"
    }
}