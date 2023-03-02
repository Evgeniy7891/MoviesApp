package ru.cft.movieapp.providers

import retrofit2.http.GET
import retrofit2.http.Query
import ru.cft.movieapp.models.MoviesModel

interface NetworkService {

    @GET("3/movie/popular?api_key=${Api.KEY}")
    suspend fun getPopularMovie() : MoviesModel

    @GET("/3/search/movie?api_key=${Api.KEY}")
    suspend fun searchMovies(@Query("query") query: String) : MoviesModel
}