package com.escmobile.hsm_hospitals.adapters

interface ListClickListener<V, D> {
    fun onRowTap(view: V, data: D)
}