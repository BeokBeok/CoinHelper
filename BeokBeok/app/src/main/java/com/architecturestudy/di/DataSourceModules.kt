package com.architecturestudy.di

import com.architecturestudy.data.source.UpbitDataSource
import com.architecturestudy.data.source.UpbitRepository
import com.architecturestudy.data.source.local.UpbitLocalDataSource
import com.architecturestudy.data.source.remote.UpbitRemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single { UpbitRepository(get(), get()) }
    single<UpbitDataSource.Local> { UpbitLocalDataSource(get()) }
    single<UpbitDataSource.Remote> { UpbitRemoteDataSource(get()) }
}