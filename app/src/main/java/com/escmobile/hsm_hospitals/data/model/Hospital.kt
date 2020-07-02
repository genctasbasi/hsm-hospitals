package com.escmobile.hsm_hospitals.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Hospital(
    @SerializedName("OrganisationID")
    val organisationID: String,

    @SerializedName("OrganisationCode")
    val organisationCode: String,

    @SerializedName("OrganisationType")
    val organisationType: String,

    @SerializedName("SubType")
    val subType: SubType,

    @SerializedName("Sector")
    val sector: String,

    @SerializedName("OrganisationStatus")
    val organisationStatus: String,

    @SerializedName("IsPimsManaged")
    val isPimsManaged: String,

    @SerializedName("OrganisationName")
    val organisationName: String,

    @SerializedName("Address1")
    val address1: String,

    @SerializedName("Address2")
    val address2: String,

    @SerializedName("Address3")
    val address3: String,

    @SerializedName("City")
    val city: String,

    @SerializedName("County")
    val county: String,

    @SerializedName("Postcode")
    val postcode: String,

    @SerializedName("Latitude")
    val latitude: String,

    @SerializedName("Longitude")
    val longitude: String,

    @SerializedName("ParentODSCode")
    val parentODSCode: String,

    @SerializedName("ParentName")
    val parentName: String,

    @SerializedName("Phone")
    val phone: String,

    @SerializedName("Email")
    val email: String,

    @SerializedName("Website")
    val website: String,

    @SerializedName("Fax")
    val fax: String

) : Parcelable