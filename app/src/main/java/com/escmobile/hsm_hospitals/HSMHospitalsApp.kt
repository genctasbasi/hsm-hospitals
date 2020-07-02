package com.escmobile.hsm_hospitals

import android.app.Application
import com.escmobile.hsm_hospitals.di.modules.appModule
import com.escmobile.hsm_hospitals.di.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HSMHospitalsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initDependencyInjectionContainer()
    }

    private fun initDependencyInjectionContainer() {
        startKoin {
            androidContext(this@HSMHospitalsApp)
            modules(
                listOf(
                    appModule,
                    viewModelModule
                )
            )
        }
    }
}