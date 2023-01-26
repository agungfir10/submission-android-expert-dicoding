package com.agungfir.veemoov.di

import com.agungfir.core.domain.usecase.MovieInteractor
import com.agungfir.core.domain.usecase.MovieUseCase
import com.agungfir.veemoov.detail.DetailViewModel
import com.agungfir.veemoov.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}