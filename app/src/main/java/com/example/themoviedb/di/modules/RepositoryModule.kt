package com.example.themoviedb.di.modules

import com.example.themoviedb.domain.movies.MoviesRepository
import com.example.themoviedb.domain.movies.MoviesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Kurganbayev Jasurbek on 14.03.2022
 **/

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {



    @Binds
    fun getMovieRepository(movieRepositoryImpl: MoviesRepositoryImpl): MoviesRepository


}

