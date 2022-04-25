package com.esmaeel.usecases.di

import com.abdallah.filesdownloader.domain.repositories.IRepository
import com.abdallah.filesdownloader.domain.repositories.ListRepository
import com.abdallah.filesdownloader.di.ContextProviders
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RepositoriesModule {

    @Provides
    @Singleton
    fun provideUsersRepository(
        contextProviders: ContextProviders
    ): IRepository =
        ListRepository( contextProviders)
}
