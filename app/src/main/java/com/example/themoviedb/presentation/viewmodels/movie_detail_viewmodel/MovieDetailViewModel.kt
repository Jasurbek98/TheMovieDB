package com.example.themoviedb.presentation.viewmodels.movie_detail_viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedb.data.remote.response.MovieItem
import com.example.themoviedb.data.remote.response.ResponseActors
import com.example.themoviedb.data.remote.response.ResponseMovieDetails
import com.example.themoviedb.domain.movies.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Jasurbek Kurganbayev 14/03/2022
 */
@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _movieDetail = MutableLiveData<ResponseMovieDetails>()
    val movieDetail: LiveData<ResponseMovieDetails> get() = _movieDetail

    private val _actors = MutableLiveData<ResponseActors>()
    val actors: LiveData<ResponseActors> get() = _actors

    private val _progressLiveData = MutableLiveData<Boolean>()
    val progressLiveData: LiveData<Boolean> get() = _progressLiveData

    private val _errorLiveData = MutableLiveData<Throwable>()
    val errorLiveData: LiveData<Throwable> get() = _errorLiveData


/*      init {
          initPopularMovie()
      }*/


    fun initMovieDetail(id: Int) {
        _progressLiveData.value = true
        viewModelScope.launch(Dispatchers.IO) {
            moviesRepository.getMovieDetail(id).collect {
                _progressLiveData.postValue(false)
                it.onSuccess { movieDetail ->
                    Log.d("TTT", "onSuccess")
                    _movieDetail.postValue(movieDetail)
                    _progressLiveData.postValue(false)
                }

                it.onFailure { throwable ->
                    _progressLiveData.postValue(false)
                    _errorLiveData.postValue(throwable)
                    Log.d("TTT", "$throwable")

                }
            }
        }
    }

    fun initMovieActors(id: Int) {
        _progressLiveData.value = true
        viewModelScope.launch(Dispatchers.IO) {
            moviesRepository.getMovieActor(id).collect {
                _progressLiveData.postValue(false)
                it.onSuccess { movieDetail ->
                    Log.d("TTT", "onSuccess")
                    _actors.postValue(movieDetail)
                    _progressLiveData.postValue(false)
                }

                it.onFailure { throwable ->
                    _progressLiveData.postValue(false)
                    _errorLiveData.postValue(throwable)
                    Log.d("TTT", "$throwable")

                }
            }
        }
    }

}