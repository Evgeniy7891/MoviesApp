package ru.cft.movieapp.ui.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.cft.movieapp.domain.usecases.GetFavoriteUseCase
import ru.cft.movieapp.models.MovieItemModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteUseCase: GetFavoriteUseCase
): ViewModel() {

    val favoriteMovieList = MutableLiveData<MutableList<MovieItemModel>>()
    val emptyList = MutableLiveData<Boolean>()

    fun gelAllMovies() = viewModelScope.launch {
        val list = getFavoriteUseCase.invoke()
        if(list.isNotEmpty()) {
            favoriteMovieList.postValue(list as MutableList<MovieItemModel>?)
            emptyList.postValue(false)
        } else {
            emptyList.postValue(true)
        }
    }
}
