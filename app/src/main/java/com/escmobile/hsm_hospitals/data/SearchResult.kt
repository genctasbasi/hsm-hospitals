package com.escmobile.hsm_hospitals.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class SearchResult(
    val id: String,
    val hospitalName: String,
    val hospitalAddress: String,
    val tintColour: Int? = null,
    val hospitalImageUrl: String? = null
) : Parcelable