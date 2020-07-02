package com.escmobile.hsm_hospitals.presentation.views

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.escmobile.hsm_hospitals.R
import com.escmobile.hsm_hospitals.presentation.viewmodels.SyncViewModel
import com.escmobile.hsm_hospitals.utils.Failure
import kotlinx.android.synthetic.main.fragment_sync.*
import org.koin.android.viewmodel.ext.android.getViewModel

class SyncFragment : BaseFragment() {

    private var downloadQueueId: Long? = null
    private val viewModel: SyncViewModel by lazy { getViewModel<SyncViewModel>() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sync, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setObservers()

        if (viewModel.hasDataFile()) onDataExists() else onNoDataExist()
    }

    private fun setObservers() {

        viewModel.downloadEnqueued.observe(viewLifecycleOwner, Observer {
            downloadQueueId = it
        })

        viewModel.dataLoadFailed.observe(viewLifecycleOwner, Observer {
            onDownloadFailed(it)
        })

        requireActivity().registerReceiver(
            onDownloadComplete,
            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        )
    }

    private val onDownloadComplete: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {

            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            if (id != downloadQueueId) return    // just to make sure correct file is downloaded

            onDownloadComplete()
        }
    }

    private fun onDownloadComplete() {
        viewModel.onDownloadCompleted()
    }

    private fun onDownloadFailed(failure: Failure) {
        progress_bar.visibility = View.GONE
        message.text = getString(R.string.loading_failed, failure.message)
    }

    override fun onDestroy() {
        super.onDestroy()
        requireActivity().unregisterReceiver(onDownloadComplete);
    }

    private fun onDataExists() {
        displayProgress(getString(R.string.loading_data))
        viewModel.loadData()
    }

    private fun onNoDataExist() {
        displayProgress(getString(R.string.sync_in_progress))
        viewModel.downloadDataFile()
    }

    private fun displayProgress(progressMessage: String) {
        message.text = progressMessage
        progress_bar.visibility = View.VISIBLE
    }
}
