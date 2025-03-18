package ru.dekabrsky.consecutivepractice2025.profile.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.dekabrsky.consecutivepractice2025.listWithDetails.data.repository.MoviesRepository
import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.repository.IMoviesRepository
import ru.dekabrsky.consecutivepractice2025.profile.presentation.model.ProfileViewState

class ProfileViewModel(
    private val repository: IMoviesRepository
): ViewModel() {

    private var mutableState = MutableStateFlow(ProfileViewState())
    val viewState = mutableState.asStateFlow()

    init {
        viewModelScope.launch {
            mutableState.update { it.copy(items = repository.getFavorites()) }
        }
    }
}