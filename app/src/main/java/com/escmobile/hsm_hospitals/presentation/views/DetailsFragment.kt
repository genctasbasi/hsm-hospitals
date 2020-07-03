package com.escmobile.hsm_hospitals.presentation.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.escmobile.hsm_hospitals.R
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_details.hospitalAddress
import kotlinx.android.synthetic.main.fragment_details.hospitalName
import kotlinx.android.synthetic.main.include_toolbar_details.*

class DetailsFragment : BaseFragment() {

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setArguments()

        button_back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setArguments() {
        val hospital = args.Hospital

        toolbar_title.text = hospital.organisationName
        hospitalName.text = hospital.organisationName
        hospitalAddress.text = "${hospital.city}, ${hospital.county} ${hospital.postcode}"

        hospital.tintColour?.let {
            hospitalImage.setColorFilter(
                ContextCompat.getColor(requireContext(), it)
            )
        }

        // TODO: Load hospital image with Glide once / if we get it.

        open_url.visibility = if (hospital.website.isBlank()) View.GONE else View.VISIBLE
        open_url.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(hospital.website)
            startActivity(openURL)
        }
    }
}
