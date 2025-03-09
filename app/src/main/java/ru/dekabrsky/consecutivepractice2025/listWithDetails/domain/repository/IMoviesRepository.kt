package ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.repository

import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.entity.MovieFullEntity
import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.entity.MovieShortEntity

interface IMoviesRepository {
    suspend fun getList(q: String): List<MovieShortEntity>

    suspend fun getById(id: String): MovieFullEntity?
}