package com.example.themoviedb.presentation.viewmodels.actor_detail_viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedb.data.remote.response.ResponseActorCredits
import com.example.themoviedb.data.remote.response.ResponsePerson
import com.example.themoviedb.domain.movies.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Jasurbek Kurganbayev 14/03/2022
 */

@HiltViewModel
class ActorDetailViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository

) : ViewModel() {

    private val _actorDetail = MutableLiveData<ResponsePerson>()
    val actorDetail: LiveData<ResponsePerson> get() = _actorDetail

    private val _actorCredits = MutableLiveData<ResponseActorCredits>()
    val actorCredits: LiveData<ResponseActorCredits> get() = _actorCredits

    private val _progressLiveData = MutableLiveData<Boolean>()
    val progressLiveData: LiveData<Boolean> get() = _progressLiveData

    private val _errorLiveData = MutableLiveData<Throwable>()
    val errorLiveData: LiveData<Throwable> get() = _errorLiveData


    fun getActorDetail(id: Int) {
        _progressLiveData.value = true
        viewModelScope.launch(Dispatchers.IO) {
            moviesRepository.getActor(id).collect {
                _progressLiveData.postValue(false)
                it.onSuccess { movieDetail ->

                    Log.d("TTT", "onSuccess")
                    _actorDetail.postValue(movieDetail)
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

    fun getActorCredits(id: Int) {
        _progressLiveData.value = true
        viewModelScope.launch(Dispatchers.IO) {
            moviesRepository.getActorCredits(id).collect {
                _progressLiveData.postValue(false)
                it.onSuccess { movieDetail ->
                    Log.d("TTT", "onSuccess")
                    _actorCredits.postValue(movieDetail)
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