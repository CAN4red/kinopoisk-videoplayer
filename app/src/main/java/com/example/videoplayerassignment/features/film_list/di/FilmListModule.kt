package com.example.videoplayerassignment.features.film_list.di

import android.content.Context
import com.example.videoplayerassignment.features.film_list.data.local.FilmDatabase
import com.example.videoplayerassignment.features.film_list.data.local.dao.FilmDao
import com.example.videoplayerassignment.features.film_list.data.remote.api.FilmListApi
import com.example.videoplayerassignment.features.film_list.data.repository.FilmListListRepositoryImpl
import com.example.videoplayerassignment.features.film_list.domain.repository.FilmListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FilmListModule {

    @Provides
    @Singleton
    fun provideFilmListApi(retrofit: Retrofit): FilmListApi {
        return retrofit.create(FilmListApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFilmListRepository(api: FilmListApi, dao: FilmDao): FilmListRepository {
        return FilmListListRepositoryImpl(api, dao)
    }

    @Provides
    @Singleton
    fun provideFilmDatabase(@ApplicationContext context: Context): FilmDatabase {
        return FilmDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideFilmDao(db: FilmDatabase): FilmDao {
        return db.filmDao
    }
}