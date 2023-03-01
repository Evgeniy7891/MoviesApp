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
import ru.cft.movieapp.data.repository.MoviesRepositoryImpl
import ru.cft.movieapp.data.room.MoviesRoomDatabase
import ru.cft.movieapp.data.room.repository.MoviesRoomRepositoryImpl
import ru.cft.movieapp.domain.model.NetworkState
import ru.cft.movieapp.domain.usecases.GetMovieUseCase
import ru.cft.movieapp.models.MoviesModel
import ru.cft.movieapp.util.REALISATION
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMoviesUseCase: GetMovieUseCase,
    @ApplicationContext context: Context
) : ViewModel() {

    val context = context

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

    fun initDatabase() {
        val daoMovie = MoviesRoomDatabase.getInstatnce(context).getMovieDao()
        REALISATION = MoviesRoomRepositoryImpl(daoMovie)
    }
}