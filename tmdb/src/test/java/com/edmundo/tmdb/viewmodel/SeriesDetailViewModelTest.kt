package com.edmundo.tmdb.viewmodel

import android.view.View
import com.edmundo.domain.model.TvShowDetail
import com.edmundo.domain.repository.TmdbRepository
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SeriesDetailViewModelTest {

    private val repository: TmdbRepository = mockk(relaxed = true)
    private lateinit var viewModel: SerieDetailViewModel

    @Before
    fun setup() {
        viewModel = SerieDetailViewModel(repository)
    }


    @Test
    fun `when serie is found by its id then I must not show a message`() {
        val serieMockk: TvShowDetail = mockk(relaxed = true)
        viewModel.run {
            tvShowDetail.value = serieMockk
            validateData()

            Assert.assertEquals(View.GONE, noTvShowFoundVisibility.value)
        }
    }


    @Test
    fun `when tvShowId is invalid then I must hide content view`() {
        val invalidId = 999999
        viewModel.run {
            getTvShow(invalidId)
            validateData()

            Assert.assertEquals(View.GONE, contendVisibility.value)
        }
    }

    @Test
    fun `when tvShowId is valid then I must content view`() {
        val invalidId = 1399
        viewModel.run {
            getTvShow(invalidId)
            validateData()

            Assert.assertEquals(View.VISIBLE, contendVisibility.value)
        }
    }



    @Test
    fun `when tvShow is null then I must show a message`() {
        val serieMockk: TvShowDetail? = null
        viewModel.run {
            tvShowDetail.value = serieMockk
            validateData()

            Assert.assertEquals(View.VISIBLE, noTvShowFoundVisibility.value)
        }
    }
}