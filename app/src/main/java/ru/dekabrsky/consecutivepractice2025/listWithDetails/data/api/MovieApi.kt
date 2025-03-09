package ru.dekabrsky.consecutivepractice2025.listWithDetails.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.dekabrsky.consecutivepractice2025.listWithDetails.data.model.MovieFullResponse
import ru.dekabrsky.consecutivepractice2025.listWithDetails.data.model.MovieShortResponse
import ru.dekabrsky.consecutivepractice2025.listWithDetails.data.model.MoviesSearchResponse

interface MovieApi {
    @GET("/")
    suspend fun getMovies(
        @Query("s") search: String,
        @Query("page") page: Int = 1
    ) : MoviesSearchResponse

    @GET("/")
    suspend fun getMovie(
        @Query("i") id: String? = null,
    ) : MovieFullResponse
}