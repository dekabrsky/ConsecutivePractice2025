package ru.dekabrsky.consecutivepractice2025.listWithDetails.data.mapper

import ru.dekabrsky.consecutivepractice2025.listWithDetails.data.model.MovieFullResponse
import ru.dekabrsky.consecutivepractice2025.listWithDetails.data.model.MoviesSearchResponse
import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.entity.MovieFullEntity
import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.entity.MovieShortEntity
import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.entity.MovieType

class MovieResponseToEntityMapper {
    fun mapSearch(response: MoviesSearchResponse) =
        response.search?.map { movie ->
            MovieShortEntity(
                id = movie.id.orEmpty(),
                title = movie.title.orEmpty(),
                type = MovieType.getByValue(movie.type),
                year = movie.year.orEmpty(),
                posterUrl = movie.posterUrl.orEmpty()
            )
        }.orEmpty()

    fun mapFull(response: MovieFullResponse) =
        MovieFullEntity(
            title = response.title.orEmpty(),
            imdbID = response.imdbID.orEmpty(),
            year = response.year.orEmpty(),
            plot = response.plot.orEmpty(),
            poster = response.poster.orEmpty(),
            ratings = response.ratings?.map {
                MovieFullEntity.Rating(
                    it.source.orEmpty(),
                    it.value.orEmpty()
                )
            }.orEmpty(),
            type = MovieType.getByValue(response.type)
        )
}