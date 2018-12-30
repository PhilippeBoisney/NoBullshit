package io.nobullshit.nobullshit.base

import android.app.Application
import io.nobullshit.nobullshit.di.appComponents
import org.koin.android.ext.android.startKoin

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, appComponents)
    }
}