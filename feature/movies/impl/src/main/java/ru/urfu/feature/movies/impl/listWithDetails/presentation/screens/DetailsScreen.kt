package ru.urfu.feature.movies.impl.listWithDetails.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import ru.urfu.feature.movies.impl.listWithDetails.data.mock.MoviesData
import ru.urfu.feature.movies.api.domain.entity.MovieFullEntity
import ru.urfu.feature.movies.api.domain.entity.MovieShortEntity
import ru.urfu.feature.movies.impl.listWithDetails.presentation.state.MovieDetailState
import ru.urfu.feature.movies.impl.listWithDetails.presentation.viewModel.DetailsViewModel
import ru.urfu.feature.movies.api.presentation.component.MovieItem
import ru.urfu.feature.uikit.theme.Spacing

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
        topBar = {
            ru.urfu.feature.uikit.components.SimpleAppBar(
                state.movie?.title.orEmpty(),
                onBackPressed
            )
        },
    ) {
        if (state.isLoading) {
            ru.urfu.feature.uikit.components.FullscreenLoading()
            return@Scaffold
        }

        state.error?.let {
            ru.urfu.feature.uikit.components.FullscreenMessage(msg = it)
            return@Scaffold
        }

        val movie = state.movie ?: return@Scaffold

        Column(
            Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
                .padding(Spacing.medium)
        ) {
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

            Column(
                Modifier
                    .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                    .padding(Spacing.medium)) {
                Text(
                    text = "Связанные фильмы",
                    style = MaterialTheme.typography.titleMedium
                )
                state.related.forEach { movie ->
                    MovieItem(item = movie)
                }
            }

            Spacer(modifier = Modifier.height(Spacing.medium))

            ru.urfu.feature.uikit.components.RatingBar(
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
        override val movie = MoviesData.moviesFull.find { it.imdbID == "tt1856101" }
        override val rating = 0f
        override val isRatingVisible = true
        override val isLoading = false
        override val error = null
        override val related: List<MovieShortEntity> = MoviesData.moviesShort.take(3)
    }, {}, {})
}