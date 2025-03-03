package ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.forward
import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.entity.MovieShortEntity
import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.repository.IMoviesRepository
import ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.screens.DetailsScreen
import ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.state.MoviesListState

class ListViewModel(
    private val repository: IMoviesRepository,
    private val navigation: StackNavContainer
): ViewModel() {

    private val mutableState = MutableMoviesListState()
    val viewState = mutableState as MoviesListState

    init {
        loadMovies()
    }

    private fun loadMovies() {
        mutableState.items = repository.getList(viewState.query)
    }

    fun onQueryChanged(query: String) {
        mutableState.query = query
        loadMovies()
    }

    fun onItemClicked(id: String) {
        navigation.forward(DetailsScreen(movieId = id))
    }

    private class MutableMoviesListState: MoviesListState {
        override var items: List<MovieShortEntity> by mutableStateOf(emptyList())
        override var query by mutableStateOf("")
        override val isEmpty get() = items.isEmpty()

    }
}