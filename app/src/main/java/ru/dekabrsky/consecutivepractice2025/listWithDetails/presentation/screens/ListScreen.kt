package ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.LocalStackNavigation
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import ru.dekabrsky.consecutivepractice2025.R
import ru.dekabrsky.consecutivepractice2025.listWithDetails.data.mock.MoviesData
import ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.viewModel.ListViewModel
import ru.urfu.feature.movies.api.presentation.component.MovieItem
import ru.urfu.feature.uikit.theme.Spacing

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
                Row(
                    Modifier.padding(Spacing.small),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = state.query,
                        onValueChange = { viewModel.onQueryChanged(it) },
                        label = { Text(stringResource(R.string.search)) },
                        modifier = Modifier.weight(1f),
                        leadingIcon = { Icon(Icons.Rounded.Search, null) }
                    )

                    BadgedBox(
                        badge = { if (state.hasBadge) Badge() },
                        Modifier.padding(Spacing.small)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "More",
                            modifier = Modifier.clickable { viewModel.onFiltersClicked() }
                        )
                    }
                }

            },
            contentWindowInsets = WindowInsets(0.dp),
        ) {
            if (state.showTypesDialog) {
                SelectionDialog(
                    onDismissRequest = { viewModel.onSelectionDialogDismissed() },
                    onConfirmation = { viewModel.onFiltersConfirmed() },
                    title = "Тип",
                    variants = state.typesVariants,
                    selectedVariants = state.selectedTypes
                ) { variant, isSelected ->
                    viewModel.onSelectedVariantChanged(variant, isSelected)
                }
            }

            if (state.isLoading) {
                ru.urfu.feature.uikit.components.FullscreenLoading()
                return@Scaffold
            }

            state.error?.let {
                ru.urfu.feature.uikit.components.FullscreenMessage(msg = it)
                return@Scaffold
            }

            if (state.isEmpty) {
                ru.urfu.feature.uikit.components.FullscreenMessage("По запросу нет результатов")
                return@Scaffold
            }

            LazyColumn(Modifier.padding(it)) {
                items(state.items) { item ->
                    MovieItem(
                        item = item,
                        Modifier
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onTap = { viewModel.onItemClicked(item.id) },
                                    onDoubleTap = { viewModel.onItemDoubleClicked(item) }
                                )
                            }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieItemPreview() {
    MovieItem(item = MoviesData.moviesShort.first())
}