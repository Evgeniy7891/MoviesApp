package ru.cft.movieapp.data.datasources

import ru.cft.movieapp.models.MoviesModel

interface MovieRemoteDataSource {
    suspend fun getMovies() : MoviesModel
    suspend fun searchMovies(name:String) : MoviesModel
}