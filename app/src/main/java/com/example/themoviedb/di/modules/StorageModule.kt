package com.example.themoviedb.di.modules

import com.example.themoviedb.data.local.LocalStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class StorageModule {

    @Provides
    @Singleton
    fun getLocalStorage(): LocalStorage = LocalStorage.instance

}