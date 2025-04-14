package ru.urfu.feature.movies.impl.listWithDetails.presentation.state

import ru.urfu.feature.movies.api.domain.entity.MovieShortEntity
import ru.urfu.feature.movies.api.domain.entity.MovieType

interface MoviesListState {
    val items: List<MovieShortEntity>
    val query: String
    val isEmpty: Boolean
    val isLoading: Boolean
    val error: String?
    val showTypesDialog: Boolean
    val typesVariants: Set<MovieType>
    val selectedTypes: Set<MovieType>
    val hasBadge: Boolean
}