package ru.cft.movieapp.providers

import retrofit2.Response
import retrofit2.http.GET
import ru.cft.movieapp.models.MoviesModel

interface NetworkService {

    @GET("3/movie/popular")
    suspend fun getPopularMovie() : Response<MoviesModel>
}