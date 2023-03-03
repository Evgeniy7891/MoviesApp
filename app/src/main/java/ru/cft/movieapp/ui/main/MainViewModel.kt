package ru.cft.movieapp.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.cft.movieapp.data.room.MoviesRoomDatabase
import ru.cft.movieapp.data.room.repository.MoviesRoomRepositoryImpl
import ru.cft.movieapp.domain.model.NetworkState
import ru.cft.movieapp.domain.usecases.GetMovieUseCase
import ru.cft.movieapp.domain.usecases.GetTvUseCase
import ru.cft.movieapp.models.MoviesModel
import ru.cft.movieapp.util.ContentModel
import ru.cft.movieapp.util.REALISATION
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMoviesUseCase: GetMovieUseCase,
    private val getTvUseCase: GetTvUseCase
) : ViewModel() {

    private val _errorMessage = MutableSharedFlow<String>()
    val errorMessage = _errorMessage.asSharedFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading = _isLoading.asStateFlow()

    private val _result = MutableStateFlow<MoviesModel?>(null)
    val result = _result.asStateFlow()

    fun getInfo() = viewModelScope.launch {
        when (val response = getMoviesUseCase.invoke()) {
            is NetworkState.Error -> _errorMessage.emit(response.throwable)
            is NetworkState.Loading -> TODO("not implemented yet")
            is NetworkState.Success -> _result.emit(response.success)
        }
    }

    private val _errorMessageTv = MutableSharedFlow<String>()
    val errorMessageTv = _errorMessageTv.asSharedFlow()

    private val _isLoadingTv = MutableStateFlow<Boolean>(false)
    val isLoadingTv = _isLoadingTv.asStateFlow()

    private val _resultTv = MutableStateFlow<MoviesModel?>(null)
    val resultTv = _resultTv.asStateFlow()

    fun getInfoTv() = viewModelScope.launch {
        when (val response = getTvUseCase.invoke()) {
            is NetworkState.Error -> _errorMessageTv.emit(response.throwable)
            is NetworkState.Loading -> TODO("not implemented yet")
            is NetworkState.Success -> _resultTv.emit(response.success)
        }
    }
}