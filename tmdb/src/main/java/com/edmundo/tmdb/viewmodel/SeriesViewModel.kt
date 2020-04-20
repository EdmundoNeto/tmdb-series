package com.edmundo.tmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.edmundo.domain.model.Show
import com.edmundo.domain.repository.TmdbRepository
import com.edmundo.tmdb.paging.PagedTvShowsDatasource
import kotlinx.coroutines.Dispatchers

class SeriesViewModel(private val repository: TmdbRepository): BaseViewModel() {
    var seriesLiveData  : LiveData<PagedList<Show>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()
        seriesLiveData  = initializedPagedListBuilder(config).build()
    }

    fun getSeries(): LiveData<PagedList<Show>> = seriesLiveData

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<Int, Show> {

        val dataSourceFactory = object : DataSource.Factory<Int, Show>() {
            override fun create(): DataSource<Int, Show> {
                return PagedTvShowsDatasource(repository, Dispatchers.IO)
            }
        }
        return LivePagedListBuilder<Int, Show>(dataSourceFactory, config)
    }
}