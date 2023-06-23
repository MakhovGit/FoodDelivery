package com.example.fooddelivery.di

import com.example.fooddelivery.ui.menu.MenuFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainKoinModule = module {
    viewModel { MenuFragmentViewModel() }
}