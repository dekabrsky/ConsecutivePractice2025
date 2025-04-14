package ru.urfu.feature.movies.impl.listWithDetails.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.urfu.feature.movies.api.domain.repository.IMoviesRepository
import ru.urfu.feature.movies.impl.listWithDetails.data.mapper.MovieResponseToEntityMapper
import ru.urfu.feature.movies.impl.listWithDetails.data.repository.MoviesRepository
import ru.urfu.feature.movies.impl.listWithDetails.presentation.viewModel.DetailsViewModel
import ru.urfu.feature.movies.impl.listWithDetails.presentation.viewModel.ListViewModel

val moviesModule = module {
    single<IMoviesRepository> { MoviesRepository(get(), get(), get()) }

    factory { MovieResponseToEntityMapper() }

    viewModel { ListViewModel(get(), it.get()) }
    viewModel { DetailsViewModel(get(), it.get(), it.get()) }
}