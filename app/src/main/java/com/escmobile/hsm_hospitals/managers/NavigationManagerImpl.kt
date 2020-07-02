package com.escmobile.hsm_hospitals.managers

import androidx.navigation.NavController
import androidx.navigation.NavDirections

class NavigationManagerImpl : NavigationManager {
    private lateinit var navController: NavController

    override fun setNavigationController(navController: NavController) {
        this.navController = navController
    }

    override fun navigateTo(direction: NavDirections) {
        this.navController.navigate(direction)
    }
}