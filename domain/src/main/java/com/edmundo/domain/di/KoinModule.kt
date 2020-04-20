package com.edmundo.domain.di

import com.edmundo.domain.TmdbApi
import com.edmundo.domain.repository.TmdbRepository
import org.koin.dsl.module


val serviceModule = module {
    single {
        TmdbApi.getApiService()
    }
}

val repositoryModule = module {
    single {
        TmdbRepository(service = get())
    }
}
