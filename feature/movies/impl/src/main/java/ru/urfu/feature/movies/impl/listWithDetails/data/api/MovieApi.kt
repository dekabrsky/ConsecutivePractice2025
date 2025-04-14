package ru.urfu.feature.movies.impl.listWithDetails.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.urfu.feature.movies.impl.listWithDetails.data.model.MovieFullResponse
import ru.urfu.feature.movies.impl.listWithDetails.data.model.MovieShortResponse
import ru.urfu.feature.movies.impl.listWithDetails.data.model.MoviesSearchResponse

interface MovieApi {
    @GET("/")
    suspend fun getMovies(
        @Query("s") search: String,
        @Query("type") type: String?,
        @Query("page") page: Int = 1
    ) : MoviesSearchResponse

    @GET("/")
    suspend fun getMovie(
        @Query("i") id: String? = null,
    ) : MovieFullResponse
}