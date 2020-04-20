package com.edmundo.tmdb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edmundo.domain.model.Genre
import com.edmundo.tmdb.R
import com.edmundo.tmdb.holder.GenresViewHolder
import com.edmundo.tmdb.holder.TmdbViewHolder


class GenresAdapter: RecyclerView.Adapter<TmdbViewHolder>() {

    private var genresList: List<Genre> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TmdbViewHolder =
        GenresViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.genre_item, null)
        )

    override fun onBindViewHolder(holder: TmdbViewHolder, position: Int) {
        val commit = genresList[position]

        holder.bind(genresList[position])
    }

    override fun getItemCount(): Int = genresList.count()

    fun setGenres(list: List<Genre>) {
        this.genresList = list
        notifyDataSetChanged()
    }
}