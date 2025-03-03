package ru.dekabrsky.consecutivepractice2025.listWithDetails.data.repository

import ru.dekabrsky.consecutivepractice2025.listWithDetails.data.mock.MoviesData
import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.repository.IMoviesRepository

class MoviesRepository: IMoviesRepository {
    override fun getList(q: String) = MoviesData.moviesShort.filter { it.title.contains(q, ignoreCase = true) }

    override fun getById(id: String) = MoviesData.moviesFull.find { it.imdbID == id }
}