package ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.back
import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.entity.MovieFullEntity
import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.entity.MovieShortEntity
import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.repository.IMoviesRepository
import ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.state.MovieDetailState
import ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.state.MoviesListState

class DetailsViewModel(
    private val repository: IMoviesRepository,
    private val navigation: StackNavContainer,
    private val id: String
): ViewModel() {


    private val mutableState = MutableDetailsState()
    val viewState = mutableState as MovieDetailState

    init {
        mutableState.movie = repository.getById(id)
    }

    fun back() {
        navigation.back()
    }

    fun onRatingChanged(rating: Float) {
        mutableState.rating = rating
    }

    private class MutableDetailsState : MovieDetailState {
        override var movie: MovieFullEntity? by mutableStateOf(null)
        override var rating: Float by mutableFloatStateOf(0f)
        override val isRatingVisible: Boolean get() = rating != 0f

    }
}