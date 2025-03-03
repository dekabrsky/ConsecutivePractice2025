package ru.dekabrsky.consecutivepractice2025.listWithDetails.data.repository

import ru.dekabrsky.consecutivepractice2025.listWithDetails.data.mock.MoviesData

class MoviesRepository {
    fun getList(q: String = "") = MoviesData.moviesShort.filter { it.title.contains(q, ignoreCase = true) }

    fun getById(id: String) = MoviesData.moviesFull.find { it.imdbID == id }
}