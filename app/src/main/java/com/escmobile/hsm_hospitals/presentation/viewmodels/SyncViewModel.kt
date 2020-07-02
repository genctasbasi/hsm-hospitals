package com.escmobile.hsm_hospitals.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.escmobile.hsm_hospitals.data.DataDownloadRepository
import com.escmobile.hsm_hospitals.data.DataLoaderRepository
import com.escmobile.hsm_hospitals.data.model.Hospital
import com.escmobile.hsm_hospitals.managers.NavigationManager
import com.escmobile.hsm_hospitals.presentation.views.SyncFragmentDirections
import com.escmobile.hsm_hospitals.utils.Failure
import com.escmobile.hsm_hospitals.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SyncViewModel(
    private val navigationManager: NavigationManager,
    private val dataLoaderRepository: DataLoaderRepository,
    private val dataDownloadRepository: DataDownloadRepository
) : ViewModel() {

    var hospitals: List<Hospital> = listOf()

    private val _downloadEnqueued = SingleLiveEvent<Long>()
    val downloadEnqueued: LiveData<Long> get() = _downloadEnqueued

    private val _dataLoadFailed = SingleLiveEvent<Failure>()
    val dataLoadFailed: LiveData<Failure> get() = _dataLoadFailed

    fun hasDataFile() = dataLoaderRepository.hasDataFile()

    fun onDownloadCompleted() {
        loadData()
    }

    fun downloadDataFile() {
        _downloadEnqueued.value = dataDownloadRepository.downloadDataFile()
    }

    fun loadData() {

        if (!hasDataFile()) {
            _dataLoadFailed.value = Failure(message = "Data file doesn't exist")
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            val response = dataLoaderRepository.loadData()

            withContext(Dispatchers.Main) {
                response.failure?.let {
                    _dataLoadFailed.value = it
                }

                response.data?.let {
                    hospitals = it
                    navigationManager.navigateTo(SyncFragmentDirections.actionSyncFragmentToSearchFragment())
                }
            }
        }
    }

    fun getHospitalByOrganizationId(organizationId: String): Hospital? =
        hospitals.firstOrNull { it.organisationID == organizationId }
}
