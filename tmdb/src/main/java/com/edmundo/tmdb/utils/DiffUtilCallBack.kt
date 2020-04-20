package com.edmundo.tmdb.utils

import androidx.recyclerview.widget.DiffUtil
import com.edmundo.domain.model.Show

class DiffUtilCallBack : DiffUtil.ItemCallback<Show>() {
    override fun areItemsTheSame(oldItem: Show, newItem: Show): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Show, newItem: Show): Boolean {
        return oldItem.id == newItem.id
                && oldItem.name == newItem.name
    }

}