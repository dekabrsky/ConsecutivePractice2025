package ru.dekabrsky.consecutivepractice2025.profile.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.urfu.feature.movies.api.domain.repository.IMoviesRepository
import ru.dekabrsky.consecutivepractice2025.profile.domain.repository.IProfileRepository
import ru.dekabrsky.consecutivepractice2025.profile.presentation.model.ProfileViewState

class ProfileViewModel(
    private val repository: IMoviesRepository,
    private val profileRepository: IProfileRepository
): ViewModel() {

    private var mutableState = MutableStateFlow(ProfileViewState())
    val viewState = mutableState.asStateFlow()

    init {
        val profile = profileRepository.getProfile()
        mutableState.update { it.copy(name = profile.name, photoUrl = profile.photoUrl) }

        viewModelScope.launch {
            mutableState.update { it.copy(movies = repository.getFavorites()) }
        }
    }
}