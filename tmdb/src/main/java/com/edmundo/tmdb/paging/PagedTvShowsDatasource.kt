package com.edmundo.tmdb.paging

import android.annotation.SuppressLint
import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.edmundo.domain.model.Show
import com.edmundo.domain.repository.TmdbRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class PagedTvShowsDatasource(private val repository: TmdbRepository,
                             private val coroutineContext: CoroutineContext) :
    PageKeyedDataSource<Int, Show>() {
    private val job = Job()
    private val scope = CoroutineScope(coroutineContext + job)

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Show>) {
        scope.launch {
            try {
                val response = repository.getMostPopularTvShow(1)
                callback.onResult(response.results, null, response.page+1)

            }catch (exception : Exception){
                Log.e("PagedTvShowsDatasource", "Failed to fetch data!")
            }

        }

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Show>) {
        scope.launch {
            try {
                val response = repository.getMostPopularTvShow(params.key)
                callback.onResult(response.results, response.page+1)

            }catch (exception : Exception){
                Log.e("PagedTvShowsDatasource", "Failed to fetch data!")
            }
        }

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Show>) {
    }

    override fun invalidate() {
        super.invalidate()
        job.cancel()
    }


}