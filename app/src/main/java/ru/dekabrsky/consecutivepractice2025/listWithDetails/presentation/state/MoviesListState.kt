package ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.state

import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.entity.MovieShortEntity
import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.entity.MovieType

interface MoviesListState {
    val items: List<MovieShortEntity>
    val query: String
    val isEmpty: Boolean
    val isLoading: Boolean
    val error: String?
    val hasBadge: Boolean
    val showTypesDialog: Boolean
    val typesVariants: Set<MovieType>
    val selectedTypes: Set<MovieType>
}