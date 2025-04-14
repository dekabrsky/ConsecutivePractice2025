package ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.state

import ru.urfu.feature.movies.api.domain.entity.MovieFullEntity
import ru.urfu.feature.movies.api.domain.entity.MovieShortEntity

interface MovieDetailState {
    val movie: MovieFullEntity?
    val rating: Float
    val isRatingVisible: Boolean
    val isLoading: Boolean
    val error: String?
    val related: List<MovieShortEntity>
}