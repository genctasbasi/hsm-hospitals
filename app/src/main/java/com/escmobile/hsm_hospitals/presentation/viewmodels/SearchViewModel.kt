package com.escmobile.hsm_hospitals.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.escmobile.hsm_hospitals.data.model.Hospital
import com.escmobile.hsm_hospitals.managers.NavigationManager
import com.escmobile.hsm_hospitals.presentation.views.SearchFragmentDirections
import com.escmobile.hsm_hospitals.utils.SingleLiveEvent

class SearchViewModel(private val navigationManager: NavigationManager) : ViewModel() {

    private val _searchResults = SingleLiveEvent<List<Hospital>>()
    val searchResults: LiveData<List<Hospital>> get() = _searchResults

    fun search(searchText: String, dataList: List<Hospital>) {

        _searchResults.value = dataList.filter {
            it.organisationName.contains(searchText, true) or
                    it.postcode.contains(searchText, true) or
                    it.city.contains(searchText, true) or
                    it.county.contains(searchText, true) or
                    it.organisationType.contains(searchText, true) or
                    it.subType.name.contains(searchText, true)
        }
    }

    fun onHospitalClick(hospital: Hospital) {
        navigationManager.navigateTo(
            SearchFragmentDirections.actionSearchFragmentToDetailsFragment(
                hospital
            )
        )
    }

}
