package com.escmobile.hsm_hospitals.data

import com.escmobile.hsm_hospitals.data.model.Hospital
import com.escmobile.hsm_hospitals.utils.Response
import java.io.File

interface DataLoaderRepository {
    fun getDataFile(): File
    fun hasDataFile(): Boolean
    fun loadData(): Response<List<Hospital>>
}