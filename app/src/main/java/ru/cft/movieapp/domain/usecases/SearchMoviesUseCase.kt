package ru.cft.movieapp.domain.usecases

import ru.cft.movieapp.domain.model.NetworkState
import ru.cft.movieapp.domain.repository.MoviesRepository
import ru.cft.movieapp.models.MoviesModel
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(private val repository: MoviesRepository){

    suspend operator fun invoke(name: String): NetworkState<MoviesModel> {
        return repository.searchMovies(name)
    }
}