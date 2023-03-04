package ru.cft.movieapp.domain.repository

import ru.cft.movieapp.domain.model.NetworkState
import ru.cft.movieapp.models.ItemDetails
import ru.cft.movieapp.models.MoviesModel

interface MoviesRepository {
    suspend fun getMovies(): NetworkState<MoviesModel>
    suspend fun searchMovies(name : String) : NetworkState<MoviesModel>
    suspend fun getTv() : NetworkState<MoviesModel>
    suspend fun getDetails(id: Int) : NetworkState<ItemDetails>
    suspend fun searchTv(name: String) : NetworkState<MoviesModel>
}