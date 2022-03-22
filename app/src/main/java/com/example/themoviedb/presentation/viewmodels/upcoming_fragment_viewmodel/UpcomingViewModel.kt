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

    private val _upcomingMovie = MutableLiveData<List<MovieItem>>()
    val upcomingMovie: LiveData<List<MovieItem>> get() = _upcomingMovie

    private val _progressLiveData = MutableLiveData(false)
    val progressLiveData: LiveData<Boolean> get() = _progressLiveData

    private val _errorLiveData = MutableLiveData<Throwable>()
    val errorLiveData: LiveData<Throwable> get() = _errorLiveData

    private var page = 1


    init {
        _upcomingMovie.postValue(emptyList())
    }


    fun initUpcomingMovie() {
        if (_progressLiveData.value == false) {
            _progressLiveData.value = true
            viewModelScope.launch(Dispatchers.IO) {
                moviesRepository.getUpcomingMovies(page).collect {
                    _progressLiveData.postValue(false)
                    it.onSuccess { response ->
                        page++
                        val tempList = _upcomingMovie.value
                        val generalList = ArrayList<MovieItem>(tempList)
                        Log.d("TTT", "onSuccess")
                        generalList.addAll(response.results!!)
                        _upcomingMovie.postValue(generalList as List<MovieItem>)
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


}