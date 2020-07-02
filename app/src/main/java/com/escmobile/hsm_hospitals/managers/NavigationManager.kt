package com.escmobile.hsm_hospitals.managers

import androidx.navigation.NavController
import androidx.navigation.NavDirections

interface NavigationManager {
    fun setNavigationController(navController: NavController)
    fun navigateTo(direction: NavDirections)
}