package com.edmundo.domain

import com.edmundo.domain.datasource.TmdbService
import com.edmundo.domain.utils.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class TmdbApi {
    companion object {

        private const val TIMEOUT: Long = 5

        private fun getHttpClient(): OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)

        fun getApiService(): TmdbService = Retrofit
            .Builder()
            .baseUrl(Constants.API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(getHttpClient().build())
            .build()
            .create(TmdbService::class.java)
    }
}