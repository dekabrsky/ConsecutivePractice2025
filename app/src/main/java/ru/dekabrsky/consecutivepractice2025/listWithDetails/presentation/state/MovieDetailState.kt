package ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.state

import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.entity.MovieFullEntity

interface MovieDetailState {
    val movie: MovieFullEntity?
    val rating: Float
    val isRatingVisible: Boolean
}