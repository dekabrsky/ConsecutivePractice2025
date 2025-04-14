package ru.urfu.feature.movies.impl.listWithDetails.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.urfu.feature.movies.impl.listWithDetails.data.api.MovieApi
import ru.urfu.feature.movies.impl.listWithDetails.data.db.MovieDatabase
import ru.urfu.feature.movies.impl.listWithDetails.data.mapper.MovieResponseToEntityMapper
import ru.urfu.feature.movies.impl.listWithDetails.data.model.MovieDbEntity
import ru.urfu.feature.movies.api.domain.entity.MovieShortEntity
import ru.urfu.feature.movies.api.domain.entity.MovieType
import ru.urfu.feature.movies.api.domain.repository.IMoviesRepository

class MoviesRepository(
    private val api: MovieApi,
    private val mapper: MovieResponseToEntityMapper,
    private val db: MovieDatabase,
) : IMoviesRepository {
    override suspend fun getList(q: String, filterTypes: Set<MovieType>?) =
        withContext(Dispatchers.IO) {
            val response = api.getMovies(
                search = q,
                type = filterTypes?.firstOrNull().takeIf { filterTypes?.size == 1 }?.name
            )
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

    override suspend fun saveFavorite(movie: MovieShortEntity) =
        withContext(Dispatchers.IO) {
            db.movieDao().insert(
                MovieDbEntity(
                    name = movie.title,
                    year = movie.year,
                    type = movie.type.name,
                    url = movie.posterUrl
                )
            )
    }

    override suspend fun getFavorites() =
        withContext(Dispatchers.IO) {
            db.movieDao().getAll().map {
                MovieShortEntity(
                    it.id.toString(),
                    it.name.orEmpty(),
                    it.year.orEmpty(),
                    MovieType.getByValue(it.type),
                    it.url.orEmpty()
                )
            }
        }
}