package com.edmundo.tmdb.di

import com.edmundo.domain.model.Show
import com.edmundo.tmdb.adapter.GenresAdapter
import com.edmundo.tmdb.adapter.SeriesAdapter
import com.edmundo.tmdb.viewmodel.SerieDetailViewModel
import com.edmundo.tmdb.viewmodel.SeriesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SeriesViewModel(repository = get()) }
    viewModel { SerieDetailViewModel(repository = get()) }
}

val adapterModule = module {
    factory { (action: (Show) -> Unit) ->
        SeriesAdapter(
            itemClickAction = action
        )
    }
    factory {
        GenresAdapter()
    }
}