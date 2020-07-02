package com.escmobile.hsm_hospitals.presentation.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.escmobile.hsm_hospitals.R

/**
 * Single activity app - this is the Nav host
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
