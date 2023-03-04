package ru.cft.movieapp.providers

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.cft.movieapp.models.MoviesModel
import ru.cft.movieapp.models.ItemDetails

interface NetworkService {

    @GET("3/movie/popular?api_key=${Api.KEY}")
    suspend fun getPopularMovie() : MoviesModel

    @GET("/3/search/movie?api_key=${Api.KEY}")
    suspend fun searchMovies(@Query("query") query: String) : MoviesModel

    @GET("/3/tv/popular?api_key=${Api.KEY}")
    suspend fun getPopularTv() : MoviesModel

    @GET("/3/movie/{id}?api_key=${Api.KEY}")
    suspend fun getDetails(@Path("id") id: Int) : ItemDetails

    @GET("/3/search/tv?api_key=${Api.KEY}")
    suspend fun searchTv(@Query("query") query: String) : MoviesModel
}