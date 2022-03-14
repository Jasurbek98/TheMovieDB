package com.example.themoviedb.utils.`typealias`

import com.example.themoviedb.data.local_models.dashboard.DataWrapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 *Created by farrukh_kh on 10/27/21 11:48 PM
 *uz.perfectalgorithm.projects.tezkor.utils.typealias
 **/
typealias StateFlowWrapper<T> = StateFlow<DataWrapper<T>>
typealias MutableStateFlowWrapper<T> = MutableStateFlow<DataWrapper<T>>

@Suppress("FunctionName")
fun <T> MutableStateFlowWrapper(value: DataWrapper<T> = DataWrapper.Empty()) =
    MutableStateFlow(value)