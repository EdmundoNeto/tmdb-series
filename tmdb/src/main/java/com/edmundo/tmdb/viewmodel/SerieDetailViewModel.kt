package com.edmundo.tmdb.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.edmundo.domain.model.TvShowDetail
import com.edmundo.domain.repository.TmdbRepository
import com.edmundo.tmdb.utils.State
import kotlinx.coroutines.launch

class SerieDetailViewModel(val repository: TmdbRepository) : BaseViewModel() {

    val tvShowDetail: MutableLiveData<TvShowDetail> = MutableLiveData()
    val noTvShowFoundVisibility = MutableLiveData<Int>().apply {
        value = View.GONE
    }

    fun validateData() {
        if(tvShowDetail.value == null)
            noTvShowFoundVisibility.value = getVisibility(true)
    }

    private fun clearEmptyWarning() {
        noTvShowFoundVisibility.value = getVisibility(false)
    }

    fun getTvShow(tvShowId: Int) {
        clearEmptyWarning()

        setState(State.LOADING)

        viewModelScope.launch {
            try {
                tvShowDetail.value = repository.getTvShowDetail(tvShowId)
                validateData()

                setState(State.SUCCESS)
            } catch (ex: Exception) {
                setState(State.ERROR)
            }
        }
    }
}