package ru.cft.movieapp.domain.usecases

import ru.cft.movieapp.domain.repository.MoviesRoomRepository
import ru.cft.movieapp.models.MovieItemModel
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(private val repository: MoviesRoomRepository) {

    suspend operator fun invoke(movieItemModel: MovieItemModel) {
        return repository.deleteMovie(movieItemModel)
    }
}