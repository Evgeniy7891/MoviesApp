package ru.cft.movieapp.data.room.repository

import androidx.lifecycle.LiveData
import ru.cft.movieapp.models.MovieItemModel


interface MoviesRoomRepository {
    val allMovies: List<MovieItemModel>
    suspend fun insertMovie(movieItemModel: MovieItemModel)
    suspend fun deleteMovie(movieItemModel: MovieItemModel)
}