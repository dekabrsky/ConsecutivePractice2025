package ru.urfu.feature.profile.presentation.model

import ru.urfu.feature.movies.api.domain.entity.MovieShortEntity

data class ProfileViewState(
    val name: String = "",
    val photoUrl: String = "",
    val movies: List<MovieShortEntity> = emptyList()
)