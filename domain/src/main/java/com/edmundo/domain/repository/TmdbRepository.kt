package com.edmundo.domain.repository

import com.edmundo.domain.SafeRequest
import com.edmundo.domain.datasource.TmdbContract
import com.edmundo.domain.datasource.TmdbService
import com.edmundo.domain.model.TvShow
import com.edmundo.domain.model.TvShowDetail

class TmdbRepository(private val service: TmdbService) :
    TmdbContract,
    SafeRequest() {

    override suspend fun getMostPopularTvShow(page: Int) = apiRequest {
        service.getMostPopularTvShow(page = page)
    }

    override suspend fun getTvShowDetail(tvShowId: Int) = apiRequest {
        service.getTvShowDetail(tv_id = tvShowId)
    }
}