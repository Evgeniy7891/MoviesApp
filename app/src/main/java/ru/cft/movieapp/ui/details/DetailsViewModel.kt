package ru.cft.movieapp.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.cft.movieapp.domain.usecases.DeleteFavoriteUseCase
import ru.cft.movieapp.domain.usecases.InsertFavoriteUseCase
import ru.cft.movieapp.models.MovieItemModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val insertFavoriteUseCase: InsertFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : ViewModel() {

    fun insert(movieItemModel: MovieItemModel) =
        viewModelScope.launch(Dispatchers.IO) {
            insertFavoriteUseCase.invoke(movieItemModel)
        }

    fun delete(movieItemModel: MovieItemModel) =
        viewModelScope.launch(Dispatchers.IO) {
            deleteFavoriteUseCase.invoke(movieItemModel)
        }
}