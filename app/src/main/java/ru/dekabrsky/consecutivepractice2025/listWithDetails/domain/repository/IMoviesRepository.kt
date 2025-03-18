package ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.repository

import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.entity.MovieFullEntity
import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.entity.MovieShortEntity
import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.entity.MovieType

interface IMoviesRepository {
    suspend fun getList(
        q: String,
        filterTypes: Set<MovieType>? = null
    ): List<MovieShortEntity>

    suspend fun getById(id: String): MovieFullEntity?
    suspend fun saveFavorite(movie: MovieShortEntity)
    suspend fun getFavorites(): List<MovieShortEntity>
}