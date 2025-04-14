package ru.urfu.feature.profile.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.urfu.feature.profile.data.ProfileRepository
import ru.urfu.feature.profile.domain.repository.IProfileRepository
import ru.urfu.feature.profile.presentation.viewModel.ProfileViewModel

val profileModule = module {
    single<IProfileRepository> { ProfileRepository() }
    viewModel { ProfileViewModel(get(), get()) }
}