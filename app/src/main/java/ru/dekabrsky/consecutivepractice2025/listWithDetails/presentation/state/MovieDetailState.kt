package ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.state

import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.entity.MovieFullEntity
import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.entity.MovieShortEntity

interface MovieDetailState {
    val movie: MovieFullEntity?
    val rating: Float
    val isRatingVisible: Boolean
    val isLoading: Boolean
    val error: String?
    val related: List<MovieShortEntity>
}