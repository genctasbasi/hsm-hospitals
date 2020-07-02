package com.escmobile.hsm_hospitals.presentation.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.escmobile.hsm_hospitals.R
import com.escmobile.hsm_hospitals.adapters.ListClickListener
import com.escmobile.hsm_hospitals.adapters.ListItem
import com.escmobile.hsm_hospitals.adapters.ListItemResult
import com.escmobile.hsm_hospitals.adapters.SearchResultAdapter
import com.escmobile.hsm_hospitals.data.model.Hospital
import com.escmobile.hsm_hospitals.extensions.toListItem
import com.escmobile.hsm_hospitals.presentation.viewmodels.SearchViewModel
import com.escmobile.hsm_hospitals.presentation.viewmodels.SyncViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.include_toolbar_search.*
import org.koin.android.viewmodel.ext.android.getViewModel

class SearchFragment : BaseFragment(), ListClickListener<View, ListItem> {

    private val searchViewModel: SearchViewModel by lazy { getViewModel<SearchViewModel>() }
    private val syncViewModel: SyncViewModel by lazy { getViewModel<SyncViewModel>() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        searchView.setOnQueryTextListener(searchQueryListener)
        observeSearchResults()
    }
    
    override fun onResume() {
        super.onResume()
        onSearchResult(syncViewModel.hospitals)
    }

    private fun observeSearchResults() {

        searchViewModel.searchResults.observe(viewLifecycleOwner,
            Observer {
                onSearchResult(it)
            })
    }

    private fun onSearchResult(dataList: List<Hospital>) {
        hideViews()
        if (dataList.isEmpty()) {
            no_results_found.visibility = View.VISIBLE
        } else {
            recycler_view.visibility = View.VISIBLE
            displayResults(dataList)
        }
    }

    private fun hideViews() {
        progress_bar.visibility = View.GONE
        recycler_view.visibility = View.GONE
        no_results_found.visibility = View.GONE
        hideKeyboard()
    }

    private fun displayResults(hospitals: List<Hospital>) {

        val listItems = buildListItems(hospitals)

        recycler_view.adapter =
            SearchResultAdapter(requireContext(), listItems, this@SearchFragment)
        recycler_view.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(context)
        recycler_view.layoutManager = linearLayoutManager
    }

    private fun buildListItems(it: List<Hospital>): MutableList<ListItem> {
        val list = mutableListOf<ListItem>()
        list.addAll(it.toListItem())
        return list
    }

    private val searchQueryListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            query?.let {
                search(it)
            }
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            if (newText?.isBlank() == true) onSearchResult(syncViewModel.hospitals)
            return true
        }
    }

    fun search(searchFor: String) {
        hideViews()
        progress_bar.visibility = View.VISIBLE
        searchViewModel.search(searchFor, syncViewModel.hospitals)
    }

    override fun onRowTap(view: View, data: ListItem) {
        if (data is ListItemResult) {
            syncViewModel.getHospitalByOrganizationId(data.searchResult.id)?.let {
                searchViewModel.onHospitalClick(it)
            }
        }
    }
}
