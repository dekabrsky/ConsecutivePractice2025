package ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.LocalStackNavigation
import com.github.terrakok.modo.stack.StackNavContainer
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import ru.dekabrsky.consecutivepractice2025.listWithDetails.data.repository.MoviesRepository
import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.entity.MovieFullEntity
import ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.state.MovieDetailState
import ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.viewModel.DetailsViewModel
import ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.viewModel.ListViewModel
import ru.dekabrsky.consecutivepractice2025.ui.components.EmptyDataBox
import ru.dekabrsky.consecutivepractice2025.ui.components.RatingBar
import ru.dekabrsky.consecutivepractice2025.ui.components.SimpleAppBar
import ru.dekabrsky.consecutivepractice2025.ui.theme.Spacing

@Parcelize
class DetailsScreen(
    override val screenKey: ScreenKey = generateScreenKey(),
    val movieId: String
) : Screen {
    @Composable
    override fun Content(modifier: Modifier) {
        val navigation = LocalStackNavigation.current

        val viewModel = koinViewModel<DetailsViewModel> { parametersOf(navigation, movieId) }
        val state = viewModel.viewState

        MovieScreenContent(
            state,
            onBackPressed = { viewModel.back() },
            onRatingChanged = { viewModel.onRatingChanged(it) }
        )
    }
}

@Composable
private fun MovieScreenContent(
    state: MovieDetailState,
    onBackPressed: () -> Unit,
    onRatingChanged: (Float) -> Unit
) {
    Scaffold(
        topBar = { SimpleAppBar(state.movie?.title.orEmpty(), onBackPressed) },
    ) {
        val movie = state.movie ?: run {
            EmptyDataBox("По запросу нет результатов")
            return@Scaffold
        }

        Column(
            Modifier
                .padding(it)
                .padding(Spacing.medium)) {
            Row {
                AsyncImage(model = movie.poster, contentDescription = null)

                Spacer(modifier = Modifier.width(Spacing.medium))

                Column {
                    Text(
                        text = movie.title,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "${movie.year} • ${stringResource(movie.type.stringRes)}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = movie.plot,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Spacer(modifier = Modifier.height(Spacing.medium))

            Row {
                movie.ratings.forEach { rating ->
                    RatingItem(rating)
                }
            }

            Spacer(modifier = Modifier.height(Spacing.medium))

            RatingBar(
                rating = state.rating,
                onRatingChanged = { onRatingChanged.invoke(it) },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(Spacing.small))

            if (state.isRatingVisible) {
                Text(
                    text = "Ваша оценка ${state.rating}/5",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
private fun RowScope.RatingItem(rating: MovieFullEntity.Rating) {
    Column(Modifier.weight(1f)) {
        Text(
            text = rating.source,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = rating.value,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview
@Composable
private fun MovieScreenContentPreview() {
    MovieScreenContent(object : MovieDetailState {
        override val movie = MoviesRepository().getById("tt1856101")
        override val rating = 0f
        override val isRatingVisible = true
    }, {}, {})
}