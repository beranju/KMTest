package com.nextgen.kmtest.di

import com.nextgen.kmtest.ui.firstscreen.FirstViewModel
import com.nextgen.kmtest.ui.secondscreen.SecondViewModel
import com.nextgen.kmtest.ui.thirdscreen.ThirdViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ThirdViewModel(get()) }
    viewModel { FirstViewModel(get()) }
    viewModel { SecondViewModel(get()) }
}