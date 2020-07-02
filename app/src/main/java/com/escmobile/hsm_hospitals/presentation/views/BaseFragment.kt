package com.escmobile.hsm_hospitals.presentation.views

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    internal fun hideKeyboard() {
        context?.let { context ->
            view?.let { view ->
                val inputManager =
                    context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(
                    view.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
            }
        }
    }
}
