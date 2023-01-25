package com.agungfir.veemoov

import android.app.Application
import com.agungfir.core.di.databaseModule
import com.agungfir.core.di.networkModule
import com.agungfir.core.di.repositoryModule
import com.agungfir.veemoov.di.useCaseModule
import com.agungfir.veemoov.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class VeeMoov : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@VeeMoov)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}