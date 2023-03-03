package ru.cft.movieapp.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.cft.movieapp.domain.model.NetworkState
import ru.cft.movieapp.domain.usecases.DeleteFavoriteUseCase
import ru.cft.movieapp.domain.usecases.GetDetailsUseCase
import ru.cft.movieapp.domain.usecases.InsertFavoriteUseCase
import ru.cft.movieapp.models.ItemDetails
import ru.cft.movieapp.models.MovieItemModel
import ru.cft.movieapp.models.MoviesModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val insertFavoriteUseCase: InsertFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val getDetailsUseCase: GetDetailsUseCase
) : ViewModel() {

    fun insert(movieItemModel: MovieItemModel) =
        viewModelScope.launch(Dispatchers.IO) {
            insertFavoriteUseCase.invoke(movieItemModel)
        }

    fun delete(movieItemModel: MovieItemModel) =
        viewModelScope.launch(Dispatchers.IO) {
           val z = deleteFavoriteUseCase.invoke(movieItemModel)
        }

    private val _errorMessage = MutableSharedFlow<String>()
    val errorMessage = _errorMessage.asSharedFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading = _isLoading.asStateFlow()

    private val _result = MutableStateFlow<ItemDetails?>(null)
    val result = _result.asStateFlow()

    fun getDetails(id: Int) = viewModelScope.launch {
        when (val response = getDetailsUseCase.invoke(id)) {
            is NetworkState.Error -> _errorMessage.emit(response.throwable)
            is NetworkState.Loading -> TODO("not implemented yet")
            is NetworkState.Success -> _result.emit(response.success)
        }
    }
}