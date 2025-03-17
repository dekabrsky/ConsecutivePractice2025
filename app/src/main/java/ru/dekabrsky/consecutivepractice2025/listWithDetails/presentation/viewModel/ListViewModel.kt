package ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.viewModel

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.forward
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.debounce
import org.koin.compose.koinInject
import org.koin.java.KoinJavaComponent.inject
import ru.dekabrsky.consecutivepractice2025.core.coroutinesUtils.launchLoadingAndError
import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.entity.MovieShortEntity
import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.entity.MovieType
import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.repository.IMoviesRepository
import ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.screens.DetailsScreen
import ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.state.MoviesListState
import java.time.Duration

class ListViewModel(
    private val repository: IMoviesRepository,
    private val navigation: StackNavContainer
): ViewModel() {

    private val prefEditor: SharedPreferences.Editor by inject(SharedPreferences.Editor::class.java)
    private val prefs: SharedPreferences by inject(SharedPreferences::class.java)

    private val dataStore: DataStore<Preferences> by inject(DataStore::class.java)
    private val typesKey = stringSetPreferencesKey(KEY_MOVIE_TYPES)

    private val mutableState = MutableMoviesListState()
    val viewState = mutableState as MoviesListState

    private val textChangesFlow = MutableStateFlow("")

    private var filterTypes: Set<MovieType> = emptySet()

    init {
        viewModelScope.launch {
            textChangesFlow
                .debounce(Duration.ofSeconds(1L))
                .collect { loadMovies() }
        }
//
//        filterTypes =
//            prefs.getStringSet(KEY_MOVIE_TYPES, emptySet())
//                ?.map { MovieType.getByValue(it) }
//                ?.toSet()
//                .orEmpty()
//        updateBadge()

        viewModelScope.launch {
            dataStore.data.collect {
                filterTypes = it[typesKey]
                    ?.map { MovieType.getByValue(it) }
                    ?.toSet()
                    .orEmpty()
                updateBadge()
            }
        }

        mutableState.typesVariants = setOf(MovieType.MOVIE, MovieType.SERIES)
    }

    private fun loadMovies() {
        val query = textChangesFlow.value

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
            mutableState.items = repository.getList(query, filterTypes)
        }
    }

    fun onQueryChanged(query: String) {
        mutableState.query = query
        viewModelScope.launch { textChangesFlow.emit(query) }
    }

    fun onItemClicked(id: String) {
        navigation.forward(DetailsScreen(movieId = id))
    }

    fun onFiltersClicked() {
        mutableState.selectedTypes = filterTypes
        mutableState.showTypesDialog = true
    }

    fun onSelectionDialogDismissed() {
        mutableState.showTypesDialog = false
    }

    fun onSelectedVariantChanged(variant: MovieType, selected: Boolean) {
        mutableState.selectedTypes = mutableState.selectedTypes.run {
            if (selected) plus(variant) else minus(variant)
        }
    }

    fun onFiltersConfirmed() {
        if (filterTypes != mutableState.selectedTypes) {
            filterTypes = mutableState.selectedTypes
            loadMovies()
            updateBadge()
//            prefEditor.putStringSet(KEY_MOVIE_TYPES, filterTypes.map { it.name }.toSet())
//            prefEditor.apply()
            viewModelScope.launch {
                dataStore.edit {
                    it[typesKey] = filterTypes.map { it.name }.toSet()
                }
            }
        }
        onSelectionDialogDismissed()
    }

    private fun updateBadge() {
        mutableState.hasBadge = filterTypes.isNotEmpty()
    }

    private inner class MutableMoviesListState: MoviesListState {
        override var items: List<MovieShortEntity> by mutableStateOf(emptyList())
        override var query by mutableStateOf("")
        override val isEmpty get() = items.isEmpty()
        override var isLoading: Boolean by mutableStateOf(false)
        override var error: String? by mutableStateOf(null)
        override var showTypesDialog: Boolean by mutableStateOf(false)
        override var typesVariants: Set<MovieType> by mutableStateOf(emptySet())
        override var selectedTypes: Set<MovieType> by mutableStateOf(emptySet())
        override var hasBadge: Boolean by mutableStateOf(false)
    }

    companion object {
        private const val MIN_QUERY_LENGTH_TO_SEARCH = 3
        private const val KEY_MOVIE_TYPES = "MOVIE_TYPES"
    }
}