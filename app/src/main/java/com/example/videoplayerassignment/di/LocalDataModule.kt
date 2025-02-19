package com.example.videoplayerassignment.di

import android.content.Context
import com.example.videoplayerassignment.data.local.dao.FilmDao
import com.example.videoplayerassignment.data.remote.FilmDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {

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