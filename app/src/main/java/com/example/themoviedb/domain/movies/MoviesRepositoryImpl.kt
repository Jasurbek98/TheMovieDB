package com.example.themoviedb.domain.movies

import android.util.Log
import androidx.paging.Pager
import com.example.themoviedb.data.local.LocalStorage
import com.example.themoviedb.data.remote.apis.MoviesApi
import com.example.themoviedb.data.remote.response.*
import com.example.themoviedb.utils.API_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import uz.perfectalgorithm.projects.tezkor.utils.coroutinescope.CoroutinesScope
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by Jasurbek Kurganbayev 13/03/2022
 */
class MoviesRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApi,
) : MoviesRepository {

    override fun getPopularMovies(page: Int): Flow<Result<ResponsePopular>> = flow {

        try {
            val response = moviesApi.getPopularMovies(page)
            if (response.isSuccessful && response.code() == 200) response.body()?.let {
                emit(Result.success(it))
            } else {
                emit(Result.failure(HttpException(response)))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override fun getTopRatedMovies(page: Int): Flow<Result<ResponsePopular>> = flow {
        try {
            val response = moviesApi.getTopRatedMovies(page)
            if (response.isSuccessful && response.code() == 200) response.body()?.let {
                emit(Result.success(it))
            } else {
                emit(Result.failure(HttpException(response)))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override fun getUpcomingMovies(page: Int): Flow<Result<ResponseUpcoming>> = flow {
        try {
            val response = moviesApi.getUpcomingMovies(page)
            if (response.isSuccessful && response.code() == 200) response.body()?.let {
                emit(Result.success(it))
            } else {
                emit(Result.failure(HttpException(response)))
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

    override fun getActor(personId: Int): Flow<Result<ResponsePerson>> = flow {
        try {
            val respone = moviesApi.getActor(personId)
            if (respone.isSuccessful && respone.code() == 200) respone.body()?.let {
                emit(Result.success(it))
            } else {
                emit(Result.failure(HttpException(respone)))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override fun getActorCredits(personId: Int): Flow<Result<ResponseActorCredits>> = flow {
        try {
            val respone = moviesApi.getActorCredits(personId)
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