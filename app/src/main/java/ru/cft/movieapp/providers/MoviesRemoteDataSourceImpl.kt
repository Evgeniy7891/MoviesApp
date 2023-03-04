package ru.cft.movieapp.providers

import retrofit2.Response
import ru.cft.movieapp.data.datasources.MovieRemoteDataSource
import ru.cft.movieapp.models.ItemDetails
import ru.cft.movieapp.models.MoviesModel
import javax.inject.Inject

class MoviesRemoteDataSourceImpl @Inject constructor(private val apiService: NetworkService) : MovieRemoteDataSource{

    override suspend fun getMovies(): MoviesModel {
        return apiService.getPopularMovie()
    }

    override suspend fun searchMovies(name: String): MoviesModel {
        return apiService.searchMovies(name)
    }

    override suspend fun getTv(): MoviesModel {
        return apiService.getPopularTv()
    }

    override suspend fun getDetails(id: Int): ItemDetails {
        return apiService.getDetails(id)
    }

    override suspend fun searchTv(name: String): MoviesModel {
       return apiService.searchTv(name)
    }
}