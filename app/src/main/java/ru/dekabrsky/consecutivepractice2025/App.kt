package ru.dekabrsky.consecutivepractice2025

import android.app.Application
import com.github.terrakok.modo.ModoDevOptions
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.dekabrsky.consecutivepractice2025.di.dbModule
import ru.dekabrsky.consecutivepractice2025.di.networkModule
import ru.dekabrsky.consecutivepractice2025.di.rootModule
import ru.urfu.feature.movies.impl.listWithDetails.di.moviesModule
import ru.urfu.feature.profile.di.profileModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(rootModule, networkModule, dbModule, profileModule, moviesModule)
        }
    }
}