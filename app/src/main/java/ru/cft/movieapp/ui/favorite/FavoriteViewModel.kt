package ru.cft.movieapp.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.cft.movieapp.models.MovieItemModel
import ru.cft.movieapp.util.REALISATION

class FavoriteViewModel : ViewModel() {

    fun gelAllMovies(): LiveData<List<MovieItemModel>> {
        return REALISATION.allMovies
}
}