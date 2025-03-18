package ru.dekabrsky.consecutivepractice2025.profile.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.screens.MovieItem
import ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.viewModel.ListViewModel
import ru.dekabrsky.consecutivepractice2025.profile.presentation.viewModel.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Parcelize
class ProfileScreen(
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {

    @Composable
    override fun Content(modifier: Modifier) {

        val viewModel = koinViewModel<ProfileViewModel>()
        val state by viewModel.viewState.collectAsState()

        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = "Мои фильмы") })
            }
        ) { innerPadding ->
            LazyColumn(Modifier.padding(innerPadding)) {
                items(state.items) {
                    MovieItem(item = it)
                }
            }
        }
    }
}