package ru.dekabrsky.consecutivepractice2025.profile.presentation.state

import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.entity.MovieShortEntity

data class ProfileViewState(
    val items: List<MovieShortEntity> = emptyList()
)