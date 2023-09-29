package com.rbs.capstonevalorant

import android.app.Application
import com.rbs.capstonevalorant.di.databaseModule
import com.rbs.capstonevalorant.di.networkModule
import com.rbs.capstonevalorant.di.repositoryModule
import com.rbs.capstonevalorant.di.useCaseModule
import com.rbs.capstonevalorant.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
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