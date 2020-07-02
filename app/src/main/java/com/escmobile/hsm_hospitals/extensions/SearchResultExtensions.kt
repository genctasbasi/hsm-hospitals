package com.escmobile.hsm_hospitals.extensions

import com.escmobile.hsm_hospitals.adapters.ListItem
import com.escmobile.hsm_hospitals.adapters.ListItemResult
import com.escmobile.hsm_hospitals.data.SearchResult
import com.escmobile.hsm_hospitals.data.model.Hospital

fun List<Hospital>.toListItem(): List<ListItem> {
    return this.map { it.toListItem() }
}

private fun Hospital.toListItem(): ListItem {
    return ListItemResult(
        SearchResult(
            this.organisationID,
            this.organisationName,
            "${this.city}, ${this.county} ${this.postcode}",
            this.tintColour
        )
    )
}
