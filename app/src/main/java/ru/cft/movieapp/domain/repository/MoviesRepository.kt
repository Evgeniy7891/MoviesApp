package ru.cft.movieapp.domain.repository

import ru.cft.movieapp.domain.model.NetworkState
import ru.cft.movieapp.models.MoviesModel

interface MoviesRepository {
    suspend fun getMovies(): NetworkState<MoviesModel>
    suspend fun searchMovies(name : String) : NetworkState<MoviesModel>
}