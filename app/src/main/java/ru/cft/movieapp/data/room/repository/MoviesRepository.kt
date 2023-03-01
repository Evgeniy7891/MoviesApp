package ru.cft.movieapp.data.room.repository

import androidx.lifecycle.LiveData
import ru.cft.movieapp.models.MovieItemModel


interface MoviesRepository {
    val allMovies: LiveData<List<MovieItemModel>>
    suspend fun insertMovie(movieItemModel: MovieItemModel, onSuccess: () -> Unit)
    suspend fun deleteMovie(movieItemModel: MovieItemModel, onSuccess: () -> Unit)
}