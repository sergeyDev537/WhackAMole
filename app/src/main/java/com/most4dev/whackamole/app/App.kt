package com.most4dev.whackamole.app

import android.app.Application
import com.most4dev.whackamole.di.appModule
import com.most4dev.whackamole.di.dataModule
import com.most4dev.whackamole.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, dataModule, domainModule))
        }
    }
}