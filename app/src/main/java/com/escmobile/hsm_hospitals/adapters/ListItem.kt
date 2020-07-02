package com.escmobile.hsm_hospitals.adapters

interface ListItem {

    enum class Type {
        RESULT,
        HEADER,
    }

    fun getListItemType(): Type
}