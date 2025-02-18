package com.example.videoplayerassignment.data.remote.api

import com.example.videoplayerassignment.data.remote.dto.FilmsList
import com.example.videoplayerassignment.data.remote.dto.FilmsListInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface FilmsApi {

    @GET("/api/v2.2/films/collections?type=$COLLECTION_TYPE&page=1")
    suspend fun getFilmsListInfo(): FilmsListInfo

    @GET("/api/v2.2/films/collections?type=$COLLECTION_TYPE&page={page}")
    suspend fun getFilmsList(@Path("page") page: Int): FilmsList

    companion object {
        private const val COLLECTION_TYPE = "TOP_POPULAR_MOVIES"
    }
}