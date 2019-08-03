package com.architecturestudy.common

import android.app.Application
import com.architecturestudy.di.dataSourceModule
import com.architecturestudy.di.getLocalServiceModule
import com.architecturestudy.di.getRemoteServiceModule
import com.architecturestudy.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@Suppress("unused")
class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(
                listOf(
                    viewModelModule,
                    dataSourceModule,
                    getLocalServiceModule(),
                    getRemoteServiceModule("https://api.upbit.com/")
                )
            )
        }
    }
}