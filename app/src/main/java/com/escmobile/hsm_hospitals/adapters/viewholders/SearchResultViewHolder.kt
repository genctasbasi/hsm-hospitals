package com.escmobile.hsm_hospitals.adapters.viewholders

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.escmobile.hsm_hospitals.R
import com.escmobile.hsm_hospitals.adapters.ListClickListener
import com.escmobile.hsm_hospitals.adapters.ListItem
import com.escmobile.hsm_hospitals.adapters.ListItemResult
import kotlinx.android.synthetic.main.row_search_result.view.*

class SearchResultViewHolder(view: View) : RecyclerView.ViewHolder(view),
    BaseViewHolder<View, ListItem> {

    private val colourList = listOf(
        R.color.hospitalColourTint1,
        R.color.hospitalColourTint2,
        R.color.hospitalColourTint3,
        R.color.hospitalColourTint4,
        R.color.hospitalColourTint5
    )

    override fun bindViews(
        context: Context,
        data: ListItem,
        listener: ListClickListener<View, ListItem>,
        position: Int
    ) {

        val searchResult = (data as ListItemResult).searchResult

        hospitalName.text = searchResult.hospitalName
        hospitalAddress.text = searchResult.hospitalAddress

        cardView.setOnClickListener {
            listener.onRowTap(it, data)
        }

        // TODO: Load hospital images when ready
        // Glide.with(hospitalImage).load(searchResult.hospitalImageUrl).into(hospitalImage)

        data.searchResult.tintColour?.let {
            hospitalImage.setColorFilter(
                ContextCompat.getColor(context, it)
            )
        }
    }

    private val cardView: View = view.search_result_card_view
    private val hospitalName: TextView = view.hospitalName
    private val hospitalAddress: TextView = view.hospitalAddress
    private val hospitalImage: ImageView = view.hospitalImage
}