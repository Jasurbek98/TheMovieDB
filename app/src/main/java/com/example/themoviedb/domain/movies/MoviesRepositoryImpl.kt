package com.example.themoviedb.domain.movies

import com.example.themoviedb.data.local.LocalStorage
import com.example.themoviedb.data.remote.apis.MoviesApi
import com.example.themoviedb.data.remote.response.ResponseActors
import com.example.themoviedb.data.remote.response.ResponseMovieDetails
import com.example.themoviedb.data.remote.response.ResponsePopular
import com.example.themoviedb.data.remote.response.ResponseUpcoming
import com.example.themoviedb.utils.API_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by Jasurbek Kurganbayev 13/03/2022
 */
class MoviesRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApi,
    private val storage: LocalStorage
) : MoviesRepository {


    override fun getPopularMovies(): Flow<Result<ResponsePopular>> = flow {
        try {
            val respone = moviesApi.getPopularMovies()
            if (respone.isSuccessful && respone.code() == 200) respone.body()?.let {
                emit(Result.success(it))
            } else {
                emit(Result.failure(HttpException(respone)))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override fun getTopRatedMovies(): Flow<Result<ResponsePopular>> = flow {
        try {
            val respone = moviesApi.getTopRatedMovies()
            if (respone.isSuccessful && respone.code() == 200) respone.body()?.let {
                emit(Result.success(it))
            } else {
                emit(Result.failure(HttpException(respone)))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override fun getUpcomingMovies(): Flow<Result<ResponseUpcoming>> = flow {
        try {
            val respone = moviesApi.getUpcomingMovies()
            if (respone.isSuccessful && respone.code() == 200) respone.body()?.let {
                emit(Result.success(it))
            } else {
                emit(Result.failure(HttpException(respone)))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override fun getMovieDetail(id: Int): Flow<Result<ResponseMovieDetails>> = flow {
        try {
            val respone = moviesApi.getMovie(id)
            if (respone.isSuccessful && respone.code() == 200) respone.body()?.let {
                emit(Result.success(it))
            } else {
                emit(Result.failure(HttpException(respone)))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override fun getMovieActor(movieId: Int): Flow<Result<ResponseActors>> = flow {
        try {
            val respone = moviesApi.getMovieActors(movieId)
            if (respone.isSuccessful && respone.code() == 200) respone.body()?.let {
                emit(Result.success(it))
            } else {
                emit(Result.failure(HttpException(respone)))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}