package com.edmundo.tmdb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.edmundo.domain.model.Show
import com.edmundo.tmdb.R
import com.edmundo.tmdb.holder.SeriesViewHolder
import com.edmundo.tmdb.holder.TmdbViewHolder
import com.edmundo.tmdb.utils.DiffUtilCallBack
import com.edmundo.tmdb.utils.setImage
import com.edmundo.tmdb.utils.toDate
import com.edmundo.tmdb.utils.toDateString
import kotlinx.android.synthetic.main.serie_item.view.*

class SeriesAdapter(private val itemClickAction: (Show) -> Unit):
    PagedListAdapter<Show, TmdbViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TmdbViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.serie_item, parent, false)
        return SeriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: TmdbViewHolder, position: Int) {

        val tvShow = getItem(position)

        tvShow?.let {
            holder.itemView.setOnClickListener {
                itemClickAction.invoke(tvShow)
            }

            holder.bind(it)
        }

    }
}