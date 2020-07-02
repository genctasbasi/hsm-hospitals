package com.escmobile.hsm_hospitals.di.modules

import android.content.res.Resources
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single<Resources> { androidContext().resources }

}