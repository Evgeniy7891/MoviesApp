package ru.cft.movieapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.cft.movieapp.data.datasources.MovieRemoteDataSource
import ru.cft.movieapp.data.repository.MoviesRepositoryImpl
import ru.cft.movieapp.domain.repository.MoviesRepository
import ru.cft.movieapp.providers.MoviesRemoteDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class CoreModule {

    @Binds
    abstract fun bindMoviesRepository(impl: MoviesRepositoryImpl) : MoviesRepository

    @Binds
    abstract fun bindMoviesRemoteDataSource(impl: MoviesRemoteDataSourceImpl) : MovieRemoteDataSource
}