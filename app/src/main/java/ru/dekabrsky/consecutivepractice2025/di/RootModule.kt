package ru.dekabrsky.consecutivepractice2025.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.dekabrsky.consecutivepractice2025.listWithDetails.data.repository.MoviesRepository
import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.repository.IMoviesRepository
import ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.viewModel.DetailsViewModel
import ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.viewModel.ListViewModel

val rootModule = module {
    single<IMoviesRepository> { MoviesRepository() }

    viewModel { ListViewModel(get(), it.get()) }
    viewModel { DetailsViewModel(get(), it.get(), it.get()) }
}