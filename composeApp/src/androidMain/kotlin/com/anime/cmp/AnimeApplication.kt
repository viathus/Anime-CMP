package com.anime.cmp

import android.app.Application
import app.initKoin
import core.common.setUpNapierLoggingDebug
import org.koin.android.ext.koin.androidContext

class AnimeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@AnimeApplication)
        }
        setUpNapierLoggingDebug()
    }
}