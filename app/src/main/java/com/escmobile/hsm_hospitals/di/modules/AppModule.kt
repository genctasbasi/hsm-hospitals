package com.escmobile.hsm_hospitals.di.modules

import android.app.DownloadManager
import android.content.Context
import android.content.res.Resources
import com.escmobile.hsm_hospitals.data.DataDownloadRepository
import com.escmobile.hsm_hospitals.data.DataDownloadRepositoryImpl
import com.escmobile.hsm_hospitals.data.DataLoaderRepository
import com.escmobile.hsm_hospitals.data.DataLoaderRepositoryImpl
import com.escmobile.hsm_hospitals.managers.NavigationManager
import com.escmobile.hsm_hospitals.managers.NavigationManagerImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single<Resources> { androidContext().resources }
    single {
        androidContext().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    }

    single<NavigationManager> {
        NavigationManagerImpl()
    }

    single<DataLoaderRepository> { DataLoaderRepositoryImpl() }
    single<DataDownloadRepository> { DataDownloadRepositoryImpl(get()) }
}