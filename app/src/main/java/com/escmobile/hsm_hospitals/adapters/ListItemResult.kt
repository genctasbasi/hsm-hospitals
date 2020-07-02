package com.escmobile.hsm_hospitals.adapters

import com.escmobile.hsm_hospitals.data.SearchResult

open class ListItemResult(
    val searchResult: SearchResult
) : ListItem {

    override fun getListItemType(): ListItem.Type {
        return ListItem.Type.RESULT
    }

}