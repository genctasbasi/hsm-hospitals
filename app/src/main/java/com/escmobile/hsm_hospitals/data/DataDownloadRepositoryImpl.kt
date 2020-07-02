package com.escmobile.hsm_hospitals.data

import android.app.DownloadManager
import android.net.Uri
import android.os.Environment

const val DATA_FILE_NAME = "Hospital.csv"
const val DATA_FILE_URL = "http://media.nhschoices.nhs.uk/data/foi/$DATA_FILE_NAME"

class DataDownloadRepositoryImpl(private val downloadManager: DownloadManager) :
    DataDownloadRepository {

    override fun downloadDataFile(): Long {

        val request = DownloadManager.Request(Uri.parse(DATA_FILE_URL))
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            .setTitle(DATA_FILE_NAME)
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                DATA_FILE_NAME
            )

        return downloadManager.enqueue(request)
    }

}