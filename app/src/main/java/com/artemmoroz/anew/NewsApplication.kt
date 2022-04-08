package com.artemmoroz.anew

import android.app.Application
import com.artemmoroz.anew.core.di.newsApplicationModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidContext(this@NewsApplication)
            modules(newsApplicationModules)
        }
    }
}