package ru.cft.movieapp.domain.usecases

import ru.cft.movieapp.domain.model.NetworkState
import ru.cft.movieapp.domain.repository.MoviesRepository
import ru.cft.movieapp.models.ItemDetails
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(private val repository: MoviesRepository) {

    suspend operator fun invoke(id : Int): NetworkState<ItemDetails> {
        return repository.getDetails(id)
    }
}