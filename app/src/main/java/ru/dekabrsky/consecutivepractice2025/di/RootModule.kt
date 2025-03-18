package ru.dekabrsky.consecutivepractice2025.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.dekabrsky.consecutivepractice2025.listWithDetails.data.mapper.MovieResponseToEntityMapper
import ru.dekabrsky.consecutivepractice2025.listWithDetails.data.repository.MoviesRepository
import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.repository.IMoviesRepository
import ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.viewModel.DetailsViewModel
import ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.viewModel.ListViewModel
import ru.dekabrsky.consecutivepractice2025.profile.presentation.model.ProfileViewState
import ru.dekabrsky.consecutivepractice2025.profile.presentation.viewModel.ProfileViewModel

val rootModule = module {
    single {
        getSharedPrefs(androidApplication())
    }

    single<SharedPreferences.Editor> {
        getSharedPrefs(androidApplication()).edit()
    }

    single {
        getDataStore(androidContext())
    }

    single<IMoviesRepository> { MoviesRepository(get(), get(), get()) }

    factory { MovieResponseToEntityMapper() }

    viewModel { ListViewModel(get(), it.get()) }
    viewModel { DetailsViewModel(get(), it.get(), it.get()) }
    viewModel { ProfileViewModel(get()) }
}

fun getSharedPrefs(androidApplication: Application): SharedPreferences {
    return androidApplication.getSharedPreferences("default",  Context.MODE_PRIVATE)
}

fun getDataStore(androidContext: Context): DataStore<Preferences> =
    PreferenceDataStoreFactory.create {
        androidContext.preferencesDataStoreFile("default")
    }
