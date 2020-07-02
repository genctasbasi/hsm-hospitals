package com.escmobile.hsm_hospitals.presentation.views

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.escmobile.hsm_hospitals.managers.NavigationManager
import org.koin.android.ext.android.inject

class LauncherFragment : BaseFragment() {

    private val navigationManager: NavigationManager by inject()

    companion object {
        const val PERMISSION_REQUEST_CODE = 1
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        readTokenFromExternalFile()
    }

    private fun readTokenFromExternalFile() {

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) requestPermissions(
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            PERMISSION_REQUEST_CODE
        ) else readExternalStoragePermissionGranted()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                readExternalStoragePermissionGranted()
            }
        }
    }

    private fun readExternalStoragePermissionGranted() {
        navigationManager.navigateTo(LauncherFragmentDirections.actionLauncherFragmentToSyncFragment())
    }
}
