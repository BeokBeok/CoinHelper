package com.architecturestudy.di

import androidx.room.Room
import com.architecturestudy.data.source.local.UpbitDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

fun getLocalServiceModule() = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            UpbitDatabase::class.java,
            "UpbitTicker.db"
        ).build()
    }
    single { get<UpbitDatabase>().upbitTickerDao() }
}