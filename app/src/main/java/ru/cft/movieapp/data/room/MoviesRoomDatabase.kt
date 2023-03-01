package ru.cft.movieapp.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.cft.movieapp.data.room.dao.MoviesDao
import ru.cft.movieapp.models.MovieItemModel


@Database(entities = [MovieItemModel::class], version = 1, exportSchema = false)
abstract class MoviesRoomDatabase : RoomDatabase() {

    abstract fun getMovieDao(): MoviesDao

}