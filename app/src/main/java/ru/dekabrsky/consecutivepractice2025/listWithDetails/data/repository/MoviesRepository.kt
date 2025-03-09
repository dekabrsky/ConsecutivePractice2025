package ru.dekabrsky.consecutivepractice2025.listWithDetails.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.dekabrsky.consecutivepractice2025.listWithDetails.data.api.MovieApi
import ru.dekabrsky.consecutivepractice2025.listWithDetails.data.mapper.MovieResponseToEntityMapper
import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.repository.IMoviesRepository

class MoviesRepository(
    private val api: MovieApi,
    private val mapper: MovieResponseToEntityMapper
) : IMoviesRepository {
    override suspend fun getList(q: String) =
        withContext(Dispatchers.IO) {
            val response = api.getMovies(q)
            if (response.response.not()) {
                throw Exception(response.error.orEmpty())
            }
            mapper.mapSearch(response)
        }

    override suspend fun getById(id: String) =
        withContext(Dispatchers.IO) {
            val response = api.getMovie(id)
            if (response.response.not()) {
                throw Exception(response.error.orEmpty())
            }
            mapper.mapFull(response)
        }
}