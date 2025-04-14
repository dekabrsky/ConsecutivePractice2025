package ru.urfu.feature.movies.impl.listWithDetails.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.back
import kotlinx.coroutines.launch
import ru.urfu.core.coroutinesUtils.launchLoadingAndError
import ru.urfu.feature.movies.api.domain.entity.MovieFullEntity
import ru.urfu.feature.movies.api.domain.entity.MovieShortEntity
import ru.urfu.feature.movies.api.domain.repository.IMoviesRepository
import ru.urfu.feature.movies.impl.listWithDetails.presentation.state.MovieDetailState

class DetailsViewModel(
    private val repository: IMoviesRepository,
    private val navigation: StackNavContainer,
    private val id: String
): ViewModel() {

    private val mutableState = MutableDetailsState()
    val viewState = mutableState as MovieDetailState

    init {
        viewModelScope.launchLoadingAndError(
            handleError = { mutableState.error = it.localizedMessage },
            updateLoading = { mutableState.isLoading = it }
        ) {
            mutableState.movie = repository.getById(id)
            mutableState.movie?.title?.let {
                launch { mutableState.related = repository.getList(it).take(3) }
            }

//            val movieDeferred = async { repository.getById(id) }
//            val relatedDeferred = async { repository.getList("Shrek").take(3) }
//
//            mutableState.movie = movieDeferred.await()
//            mutableState.related = relatedDeferred.await()
        }
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
        override var isLoading: Boolean by mutableStateOf(false)
        override var error: String? by mutableStateOf(null)
        override var related: List<MovieShortEntity> by mutableStateOf(emptyList())
    }
}