package com.escmobile.hsm_hospitals.adapters.viewholders

import android.content.Context
import com.escmobile.hsm_hospitals.adapters.ListClickListener

interface BaseViewHolder<V, D> {
    fun bindViews(context: Context, data: D, listener: ListClickListener<V, D>, position: Int)
}