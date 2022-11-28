package com.example.binders

import com.example.binders.activities.main.LaunchesDataFactory
import com.example.binders.activities.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class Modules(application: BinderApplication) {
    val all = module {
        factory { LaunchesDataFactory() }
        viewModel { MainViewModel(application, get()) }
    }
}