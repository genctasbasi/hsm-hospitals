package com.escmobile.hsm_hospitals.di.modules

import android.content.res.Resources
import com.escmobile.hsm_hospitals.managers.NavigationManager
import com.escmobile.hsm_hospitals.managers.NavigationManagerImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single<Resources> { androidContext().resources }

    single<NavigationManager> {
        NavigationManagerImpl()
    }

}