package com.abdallah.filesdownloader.di

import com.abdallah.filesdownloader.data.local.FileManager
import com.abdallah.filesdownloader.data.remote.ApiService
import com.abdallah.filesdownloader.domain.repositories.IRepository
import com.abdallah.filesdownloader.domain.repositories.FilesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RepositoriesModule {


    @Singleton
    @Provides
    fun fileManager(
    ): FileManager =
        FileManager()

    @Provides
    @Singleton
    fun provideUsersRepository(
        fileManager: FileManager,
        apiService: ApiService,
        contextProviders: ContextProviders
    ): IRepository =
        FilesRepository(apiService,fileManager,contextProviders)
}
