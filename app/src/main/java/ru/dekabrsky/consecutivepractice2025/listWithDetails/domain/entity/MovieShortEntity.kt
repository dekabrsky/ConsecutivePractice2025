package ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.entity

import androidx.annotation.StringRes
import ru.dekabrsky.consecutivepractice2025.R

class MovieShortEntity(
    val id: String,
    val title: String,
    val year: String,
    val type: MovieType,
    val posterUrl: String,
)

enum class MovieType(@StringRes val stringRes: Int) {
    MOVIE(R.string.movie),
    SERIES(R.string.series),
    OTHER(R.string.other);

    companion object {
        fun getByValue(type: String?) = entries.find { it.name.equals(type, ignoreCase = true) } ?: OTHER
    }
}