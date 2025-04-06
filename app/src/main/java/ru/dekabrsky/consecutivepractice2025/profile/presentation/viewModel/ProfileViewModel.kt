package ru.dekabrsky.consecutivepractice2025.profile.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.repository.IMoviesRepository
import ru.dekabrsky.consecutivepractice2025.profile.presentation.state.ProfileViewState

class ProfileViewModel(
    private val repository: IMoviesRepository
): ViewModel() {


    private var mutableState = MutableStateFlow(ProfileViewState())
    val viewState = mutableState.asStateFlow()

    init {
        updateFavorites()
    }
    fun onUpdateClick() {
        updateFavorites()
    }

    private fun updateFavorites() {
        viewModelScope.launch {
            mutableState.update { it.copy(items = repository.getFavorites()) }
        }
    }
}