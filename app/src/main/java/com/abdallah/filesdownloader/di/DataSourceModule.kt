package com.abdallah.filesdownloader.di

import com.abdallah.filesdownloader.data.remote.ApiService
import com.abdallah.filesdownloader.data.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {



    @Provides
    @Singleton
    fun provideRemoteDataSource(): ApiService {
        return RemoteDataSource()
    }

}