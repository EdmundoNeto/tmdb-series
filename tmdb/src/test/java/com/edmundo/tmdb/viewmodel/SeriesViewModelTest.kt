package com.edmundo.tmdb.viewmodel

import android.view.View
import androidx.paging.PagedList
import com.edmundo.domain.model.Show
import com.edmundo.domain.repository.TmdbRepository
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SeriesViewModelTest {

    private val repository: TmdbRepository = mockk(relaxed = true)
    private lateinit var viewModel: SeriesViewModel

    @Before
    fun setup() {
        viewModel = SeriesViewModel(repository)
    }
}