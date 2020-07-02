package com.escmobile.hsm_hospitals.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.escmobile.hsm_hospitals.R
import com.escmobile.hsm_hospitals.adapters.viewholders.BaseViewHolder
import com.escmobile.hsm_hospitals.adapters.viewholders.SearchResultViewHolder

class SearchResultAdapter(
    private val context: Context,
    private var items: List<ListItem>,
    private val listener: ListClickListener<View, ListItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun updateData(newItems: List<ListItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) = ListItem.Type.RESULT.ordinal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SearchResultViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.row_search_result,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        @Suppress("UNCHECKED_CAST")
        (holder as BaseViewHolder<View, ListItem>).bindViews(
            context,
            items[position],
            listener,
            position
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }
}