package ru.cft.movieapp.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.cft.movieapp.data.room.repository.MoviesRoomRepositoryImpl
import ru.cft.movieapp.models.MovieItemModel
import ru.cft.movieapp.util.REALISATION

class DetailsViewModel : ViewModel() {

    fun insert(movieItemModel: MovieItemModel, onSuccess: () -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            REALISATION.insertMovie(movieItemModel){
                onSuccess
            }
        }
    fun delete(movieItemModel: MovieItemModel, onSuccess: () -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            REALISATION.deleteMovie(movieItemModel){
                onSuccess
            }
        }
}