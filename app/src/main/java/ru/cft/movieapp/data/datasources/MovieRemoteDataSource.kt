package ru.cft.movieapp.data.datasources

import retrofit2.Response
import ru.cft.movieapp.models.MoviesModel

interface MovieRemoteDataSource {

    suspend fun getMovies() : MoviesModel
}