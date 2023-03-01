package ru.cft.movieapp.data.room.repository

import androidx.lifecycle.LiveData
import ru.cft.movieapp.data.room.dao.MoviesDao
import ru.cft.movieapp.models.MovieItemModel
import javax.inject.Inject

class MoviesRoomRepositoryImpl @Inject constructor (private val movieDao: MoviesDao) : MoviesRepository {

    override val allMovies: LiveData<List<MovieItemModel>>
    get() = movieDao.getAllMovies()

    override suspend fun insertMovie(movieItemModel: MovieItemModel, onSuccess: () -> Unit) {
        movieDao.insert(movieItemModel)
        onSuccess()
    }

    override suspend fun deleteMovie(movieItemModel: MovieItemModel, onSuccess: () -> Unit) {
        movieDao.delete(movieItemModel)
        onSuccess()
    }
}