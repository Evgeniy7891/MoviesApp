package ru.cft.movieapp.data.room.repository

import ru.cft.movieapp.data.room.dao.MoviesDao
import ru.cft.movieapp.domain.repository.MoviesRoomRepository
import ru.cft.movieapp.models.MovieItemModel
import javax.inject.Inject

class MoviesRoomRepositoryImpl @Inject constructor (private val movieDao: MoviesDao) :
    MoviesRoomRepository {

    override val allMovies: List<MovieItemModel>
    get() = movieDao.getAllMovies()

    override suspend fun insertMovie(movieItemModel: MovieItemModel) {
        movieDao.insert(movieItemModel)
    }

    override suspend fun deleteMovie(movieItemModel: MovieItemModel) {
        movieDao.delete(movieItemModel)
    }
}