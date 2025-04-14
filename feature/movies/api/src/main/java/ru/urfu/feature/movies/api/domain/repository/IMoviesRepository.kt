package ru.urfu.feature.movies.api.domain.repository

import ru.urfu.feature.movies.api.domain.entity.MovieFullEntity
import ru.urfu.feature.movies.api.domain.entity.MovieShortEntity
import ru.urfu.feature.movies.api.domain.entity.MovieType

interface IMoviesRepository {
    suspend fun getList(
        q: String,
        filterTypes: Set<MovieType>? = null
    ): List<MovieShortEntity>

    suspend fun getById(id: String): MovieFullEntity?
    suspend fun saveFavorite(movie: MovieShortEntity)
    suspend fun getFavorites(): List<MovieShortEntity>
}