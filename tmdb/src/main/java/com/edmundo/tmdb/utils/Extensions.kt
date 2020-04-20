package com.edmundo.tmdb.utils

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.edmundo.domain.utils.Constants
import com.edmundo.tmdb.R
import java.text.SimpleDateFormat
import java.util.*


fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, expression: (T?) -> Unit) {
    liveData.observe(this, Observer(expression))
}

fun ImageView.setImage(url: String?) =
    Glide.with(this)
        .load("${Constants.API_IMAGES_BASE_URL}$url")
        .error(R.drawable.ic_broken_image_black_24dp)
        .fitCenter()
        .into(this)
        .let { Unit }

fun String.toDate(): Date? =
    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(this)

@SuppressLint("DefaultLocale")
fun Date.toDateString(): String =
    SimpleDateFormat(
        "dd/MM/yyyy",
        Locale.forLanguageTag("pt-BR")
    ).format(this)