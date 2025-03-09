package ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.LocalStackNavigation
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import ru.dekabrsky.consecutivepractice2025.R
import ru.dekabrsky.consecutivepractice2025.listWithDetails.data.mock.MoviesData
import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.entity.MovieShortEntity
import ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.viewModel.ListViewModel
import ru.dekabrsky.consecutivepractice2025.ui.components.FullscreenMessage
import ru.dekabrsky.consecutivepractice2025.ui.components.FullscreenLoading
import ru.dekabrsky.consecutivepractice2025.ui.theme.Spacing

@Parcelize
class ListScreen(
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {
    @Composable
    override fun Content(modifier: Modifier) {
        val navigation = LocalStackNavigation.current

        val viewModel = koinViewModel<ListViewModel> { parametersOf(navigation) }
        val state = viewModel.viewState
        
        Scaffold(
            topBar = {
                TextField(
                    value = state.query,
                    onValueChange = { viewModel.onQueryChanged(it) },
                    label = { Text(stringResource(R.string.search)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Spacing.small),
                    leadingIcon = { Icon(Icons.Rounded.Search, null) }
                )
            },
            contentWindowInsets = WindowInsets(0.dp),
        ) {
            if (state.isLoading) {
                FullscreenLoading()
                return@Scaffold
            }

            state.error?.let {
                FullscreenMessage(msg = it)
                return@Scaffold
            }

            if (state.isEmpty) {
                FullscreenMessage("По запросу нет результатов")
                return@Scaffold
            }

            LazyColumn(Modifier.padding(it)) {
                items(state.items) {
                    MovieItem(
                        item = it,
                        Modifier.clickable { viewModel.onItemClicked(it.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun MovieItem(
    item: MovieShortEntity,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .padding(Spacing.medium)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = item.posterUrl,
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(Spacing.medium))

        Column {
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "${item.year} • ${stringResource(item.type.stringRes)}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieItemPreview() {
    MovieItem(item = MoviesData.moviesShort.first())
}