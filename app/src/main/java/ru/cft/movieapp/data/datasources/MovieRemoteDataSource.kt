package ru.cft.movieapp.data.datasources

import ru.cft.movieapp.models.ItemDetails
import ru.cft.movieapp.models.MoviesModel

interface MovieRemoteDataSource {
    suspend fun getMovies() : MoviesModel
    suspend fun searchMovies(name:String) : MoviesModel
    suspend fun getTv () : MoviesModel
    suspend fun getDetails (id: Int) : ItemDetails
}