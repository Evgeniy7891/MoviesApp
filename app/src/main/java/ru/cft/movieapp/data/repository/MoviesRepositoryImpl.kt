package ru.cft.movieapp.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import ru.cft.movieapp.data.datasources.MovieRemoteDataSource
import ru.cft.movieapp.data.room.dao.MoviesDao
import ru.cft.movieapp.di.IoDispatcher
import ru.cft.movieapp.domain.model.NetworkState
import ru.cft.movieapp.domain.repository.MoviesRepository
import ru.cft.movieapp.models.ItemDetails
import ru.cft.movieapp.models.MoviesModel
import ru.cft.movieapp.util.safeApiCall
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val remoteDataSource: MovieRemoteDataSource
) : MoviesRepository {

    override suspend fun getMovies(): NetworkState<MoviesModel> {
        return safeApiCall(ioDispatcher) {
            remoteDataSource.getMovies()
        }
    }

    override suspend fun searchMovies(name: String): NetworkState<MoviesModel> {
       return safeApiCall(ioDispatcher) {
           remoteDataSource.searchMovies(name)
       }
    }

    override suspend fun getTv(): NetworkState<MoviesModel> {
        return safeApiCall(ioDispatcher) {
         remoteDataSource.getTv()
        }
    }

    override suspend fun getDetails(id: Int): NetworkState<ItemDetails> {
        return safeApiCall(ioDispatcher) {
            remoteDataSource.getDetails(id)
        }
    }

    override suspend fun searchTv(name: String): NetworkState<MoviesModel> {
        return safeApiCall(ioDispatcher) {
            remoteDataSource.searchTv(name)
        }
    }
}