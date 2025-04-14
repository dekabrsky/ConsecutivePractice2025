package ru.dekabrsky.consecutivepractice2025.profile.presentation.model

import ru.urfu.feature.movies.api.domain.entity.MovieShortEntity

data class ProfileViewState(
    val name: String = "",
    val photoUrl: String = "",
    val movies: List<MovieShortEntity> = emptyList()
)