package ru.cft.movieapp.providers

import retrofit2.Response
import ru.cft.movieapp.data.datasources.MovieRemoteDataSource
import ru.cft.movieapp.models.MoviesModel
import javax.inject.Inject

class MoviesRemoteDataSourceImpl @Inject constructor(private val apiService: NetworkService) : MovieRemoteDataSource{

    override suspend fun getMovies(): MoviesModel {
        return apiService.getPopularMovie()
    }
}