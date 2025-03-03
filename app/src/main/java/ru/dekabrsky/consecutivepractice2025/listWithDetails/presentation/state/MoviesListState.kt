package ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.state

import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.entity.MovieShortEntity

interface MoviesListState {
    val items: List<MovieShortEntity>
    val query: String
    val isEmpty: Boolean
}