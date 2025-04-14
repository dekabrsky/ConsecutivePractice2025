package ru.dekabrsky.consecutivepractice2025.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.urfu.feature.movies.impl.listWithDetails.data.mapper.MovieResponseToEntityMapper
import ru.urfu.feature.movies.impl.listWithDetails.data.repository.MoviesRepository
import ru.urfu.feature.movies.api.domain.repository.IMoviesRepository
import ru.urfu.feature.movies.impl.listWithDetails.presentation.viewModel.DetailsViewModel
import ru.urfu.feature.movies.impl.listWithDetails.presentation.viewModel.ListViewModel

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
}

fun getSharedPrefs(androidApplication: Application): SharedPreferences {
    return androidApplication.getSharedPreferences("default",  Context.MODE_PRIVATE)
}

fun getDataStore(androidContext: Context): DataStore<Preferences> =
    PreferenceDataStoreFactory.create {
        androidContext.preferencesDataStoreFile("default")
    }
