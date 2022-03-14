package com.example.themoviedb.presentation.viewmodels.upcoming_fragment_viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedb.data.remote.response.MovieItem
import com.example.themoviedb.domain.movies.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Jasurbek Kurganbayev 14/03/2022
 */

@HiltViewModel
class UpcomingViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _popularMovie = MutableLiveData<List<MovieItem>>()
    val popularMovie: LiveData<List<MovieItem>> get() = _popularMovie

    private val _progressLiveData = MutableLiveData<Boolean>()
    val progressLiveData: LiveData<Boolean> get() = _progressLiveData

    private val _errorLiveData = MutableLiveData<Throwable>()
    val errorLiveData: LiveData<Throwable> get() = _errorLiveData

/*
    init {
        initPopularMovie()
    }*/


    fun initPopularMovie() {
        _progressLiveData.value = true
        viewModelScope.launch(Dispatchers.IO) {
            moviesRepository.getUpcomingMovies().collect {
                Log.d("TTTT", "getUpcomingMovies method")

                _progressLiveData.postValue(false)
                it.onSuccess { response ->
                    Log.d("TTTT", "onSuccess")
                    _popularMovie.postValue(response.results!!)
                    _progressLiveData.postValue(false)
                }

                it.onFailure { throwable ->
                    _progressLiveData.postValue(false)
                    _errorLiveData.postValue(throwable)
                    Log.d("TTTT", "$throwable")

                }
            }
        }
    }


}