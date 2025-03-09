package ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.forward
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.debounce
import kotlinx.coroutines.yield
import ru.dekabrsky.consecutivepractice2025.core.coroutinesUtils.launchLoadingAndError
import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.entity.MovieShortEntity
import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.repository.IMoviesRepository
import ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.screens.DetailsScreen
import ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.state.MoviesListState
import java.time.Duration

class ListViewModel(
    private val repository: IMoviesRepository,
    private val navigation: StackNavContainer
): ViewModel() {

    private val mutableState = MutableMoviesListState()
    val viewState = mutableState as MoviesListState

    private val textChangesFlow = MutableStateFlow("")

    init {
        viewModelScope.launch {
            textChangesFlow
                .debounce(Duration.ofSeconds(1L))
                .collect { loadMovies(it) }
        }
    }

    private fun loadMovies(query: String) {
        mutableState.items = emptyList()
        mutableState.error = null

        if (query.length < MIN_QUERY_LENGTH_TO_SEARCH) return

        // Вариант 1
        /* viewModelScope.launch {
            try {
                mutableState.isLoading = true
                mutableState.items = repository.getList(viewState.query)
            } catch (e: Exception) {
                mutableState.error = e.localizedMessage
            } finally {
                mutableState.isLoading = false
            }
        } */

        // Вариант 2
        /* viewModelScope.launch {
            mutableState.isLoading = true

            val result = runCatching { repository.getList(viewState.query) }

            if (result.isSuccess) {
                mutableState.items = result.getOrDefault(emptyList())
            } else {
                mutableState.error = result.exceptionOrNull()?.localizedMessage
            }

            mutableState.isLoading = false
        } */

        // Вариант 3
        viewModelScope.launchLoadingAndError(
            handleError = { mutableState.error = it.localizedMessage },
            updateLoading = { mutableState.isLoading = it }
        ) {
            mutableState.items = repository.getList(query)
        }
    }

    fun onQueryChanged(query: String) {
        mutableState.query = query
        viewModelScope.launch { textChangesFlow.emit(query) }
    }

    fun onItemClicked(id: String) {
        navigation.forward(DetailsScreen(movieId = id))
    }

    private class MutableMoviesListState: MoviesListState {
        override var items: List<MovieShortEntity> by mutableStateOf(emptyList())
        override var query by mutableStateOf("")
        override val isEmpty get() = items.isEmpty()
        override var isLoading: Boolean by mutableStateOf(false)
        override var error: String? by mutableStateOf(null)
    }

    companion object {
        private const val MIN_QUERY_LENGTH_TO_SEARCH = 3
    }
}