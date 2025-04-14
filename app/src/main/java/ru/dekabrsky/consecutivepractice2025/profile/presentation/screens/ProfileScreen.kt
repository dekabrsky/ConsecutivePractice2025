package ru.dekabrsky.consecutivepractice2025.profile.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import ru.urfu.feature.movies.api.presentation.component.MovieItem
import ru.dekabrsky.consecutivepractice2025.profile.presentation.viewModel.ProfileViewModel
import ru.urfu.feature.uikit.theme.Spacing

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
                TopAppBar(
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = state.name, modifier = Modifier.weight(1f))
                            AsyncImage(
                                model = state.photoUrl,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .padding(horizontal = Spacing.medium)
                                    .size(48.dp)
                                    .clip(CircleShape)
                            )
                        }
                })
            },
            contentWindowInsets = WindowInsets(0, 0, 0, 0)
        ) { innerPadding ->
            LazyColumn(Modifier.padding(innerPadding)) {
                items(state.movies) {
                    MovieItem(item = it)
                }
            }
        }
    }
}