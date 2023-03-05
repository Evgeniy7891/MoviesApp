package ru.cft.movieapp.domain.repository

import ru.cft.movieapp.models.MovieItemModel

interface MoviesRoomRepository {
    val allMovies: List<MovieItemModel>
    suspend fun insertMovie(movieItemModel: MovieItemModel)
    suspend fun deleteMovie(movieItemModel: MovieItemModel)
}