package com.escmobile.hsm_hospitals.data

interface DataDownloadRepository {
    fun downloadDataFile(): Long
}