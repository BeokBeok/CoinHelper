package com.architecturestudy.di

import com.architecturestudy.market.UpbitViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { UpbitViewModel(get()) }
}