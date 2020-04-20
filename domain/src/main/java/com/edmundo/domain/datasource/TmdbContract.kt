package com.edmundo.domain.datasource

import com.edmundo.domain.model.TvShow
import com.edmundo.domain.model.TvShowDetail

interface TmdbContract {

    suspend fun getMostPopularTvShow(page: Int): TvShow

    suspend fun getTvShowDetail(movie_id: Int): TvShowDetail
}