package com.edmundo.domain.datasource

import com.edmundo.domain.model.TvShow
import com.edmundo.domain.model.TvShowDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbService {

    @GET("/3/tv/popular")
    suspend fun getMostPopularTvShow(
        @Query("api_key") api_key: String = "dfc08a9f18daa5fe286dccd119b42f98",
        @Query("language") language: String  = "pt-BR",
        @Query("page") page: Int
    ): Response<TvShow>

    @GET("/3/tv/{tv_id}")
    suspend fun getTvShowDetail(
        @Path("tv_id") tv_id: Int,
        @Query("api_key") api_key: String = "dfc08a9f18daa5fe286dccd119b42f98",
        @Query("language") language: String = "pt-BR"
    ): Response<TvShowDetail>
}