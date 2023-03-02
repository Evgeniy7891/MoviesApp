package ru.cft.movieapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.cft.movieapp.domain.model.NetworkState
import ru.cft.movieapp.domain.usecases.SearchMoviesUseCase
import ru.cft.movieapp.models.MoviesModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase
) : ViewModel() {

    private val _errorMessage = MutableSharedFlow<String>()
    val errorMessage = _errorMessage.asSharedFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading = _isLoading.asStateFlow()

    private val _result = MutableStateFlow<MoviesModel?>(null)
    val result = _result.asStateFlow()

    fun getInfo(name : String) = viewModelScope.launch {
        when (val response = searchMoviesUseCase.invoke(name)) {
            is NetworkState.Error -> _errorMessage.emit(response.throwable)
            is NetworkState.Loading -> TODO("not implemented yet")
            is NetworkState.Success -> _result.emit(response.success)
        }
    }
}