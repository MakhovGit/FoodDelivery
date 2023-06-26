package com.example.fooddelivery.di

import com.example.fooddelivery.data.retrofit.DictRemoteDataSource
import com.example.fooddelivery.repository.Repository
import com.example.fooddelivery.repository.RepositoryImpl
import com.example.fooddelivery.ui.menu.MenuFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainKoinModule = module {
    single { DictRemoteDataSource() }
    single<Repository> { RepositoryImpl(dataSource = get()) }
    viewModel { MenuFragmentViewModel(repository = get()) }
}