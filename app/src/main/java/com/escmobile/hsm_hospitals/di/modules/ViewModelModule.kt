package com.escmobile.hsm_hospitals.di.modules

import com.escmobile.hsm_hospitals.presentation.viewmodels.SearchViewModel
import com.escmobile.hsm_hospitals.presentation.viewmodels.SyncViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { SearchViewModel(get()) }
    single { SyncViewModel(get(), get(), get()) }
}