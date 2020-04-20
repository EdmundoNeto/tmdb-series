package com.edmundo.tmdb.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.edmundo.domain.model.Genre
import com.edmundo.domain.model.Show
import com.edmundo.tmdb.R
import com.edmundo.tmdb.utils.setImage
import com.edmundo.tmdb.utils.toDate
import com.edmundo.tmdb.utils.toDateString
import kotlinx.android.synthetic.main.genre_item.view.*
import kotlinx.android.synthetic.main.serie_item.view.*

abstract class TmdbViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(T: Any)
}

class SeriesViewHolder(private val view: View) : TmdbViewHolder(view) {
    override fun bind(T: Any) =
        with(view) {
            serie_thumb.setImage((T as Show).poster_path)
            tvSerieTitle.text = T.name
            tvSerieDate.text = context.getString(
                R.string.first_appearence,
                T.first_air_date.toDate()?.toDateString().orEmpty())
            tvSeriePercentage.text = context.getString(
                R.string.vote_rate,
                T.vote_average.toString())
        }
}

class GenresViewHolder(private val view: View) : TmdbViewHolder(view) {
    override fun bind(T: Any) =
        with(view) {
            genre.text = (T as Genre).name
        }
}