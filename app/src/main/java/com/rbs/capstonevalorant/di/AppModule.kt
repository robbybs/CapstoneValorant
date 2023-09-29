package com.rbs.capstonevalorant.di

import com.rbs.capstonevalorant.presentation.DetailViewModel
import com.rbs.capstonevalorant.presentation.MainViewModel
import com.rbs.core.domain.usecase.AgentsInteractor
import com.rbs.core.domain.usecase.AgentsUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<AgentsUseCase> { AgentsInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    //viewModel { com.rbs.favorite.FavoriteViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}