package ru.cft.movieapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.cft.movieapp.data.room.dao.MoviesDao
import ru.cft.movieapp.models.MovieItemModel

@Database(entities = [MovieItemModel::class], version = 2, exportSchema = false)
abstract class MoviesRoomDatabase : RoomDatabase() {

    abstract fun getMovieDao(): MoviesDao

}