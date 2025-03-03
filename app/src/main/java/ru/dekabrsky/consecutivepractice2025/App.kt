package ru.dekabrsky.consecutivepractice2025

import android.app.Application
import com.github.terrakok.modo.ModoDevOptions
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.dekabrsky.consecutivepractice2025.di.rootModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(rootModule)
        }
    }
}