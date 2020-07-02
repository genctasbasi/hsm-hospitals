package com.escmobile.hsm_hospitals.presentation.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.escmobile.hsm_hospitals.R
import com.escmobile.hsm_hospitals.managers.NavigationManager
import org.koin.android.ext.android.inject

/**
 * Single activity app - this is the Nav host
 */
class MainActivity : AppCompatActivity() {

    private val navigationManager: NavigationManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigationManager.setNavigationController(Navigation.findNavController(this, R.id.nav_host))
    }
}
