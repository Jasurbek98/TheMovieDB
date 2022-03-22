package com.example.themoviedb.domain.movies

import com.example.themoviedb.data.remote.response.*
import kotlinx.coroutines.flow.Flow

/**
 * Created by Jasurbek Kurganbayev 13/03/2022
 */
interface MoviesRepository {


    fun getPopularMovies(page: Int): Flow<Result<ResponsePopular>>

    fun getTopRatedMovies(page: Int): Flow<Result<ResponsePopular>>

    fun getUpcomingMovies(page: Int): Flow<Result<ResponseUpcoming>>

    fun getMovieDetail(id: Int): Flow<Result<ResponseMovieDetails>>

    fun getMovieActor(movieId: Int): Flow<Result<ResponseActors>>

    fun getActor(personId: Int): Flow<Result<ResponsePerson>>

    fun getActorCredits(personId: Int): Flow<Result<ResponseActorCredits>>
}