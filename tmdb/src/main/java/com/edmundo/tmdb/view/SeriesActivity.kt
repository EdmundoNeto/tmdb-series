package com.edmundo.tmdb.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.edmundo.domain.model.Show
import com.edmundo.tmdb.R
import com.edmundo.tmdb.adapter.SeriesAdapter
import com.edmundo.tmdb.utils.observe
import com.edmundo.tmdb.viewmodel.SeriesViewModel
import kotlinx.android.synthetic.main.activity_series.*
import kotlinx.android.synthetic.main.layout_error.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import org.koin.android.viewmodel.ext.android.viewModel

class SeriesActivity: AppCompatActivity() {

    private val viewModel: SeriesViewModel by viewModel()
    private val listAdapter: SeriesAdapter by inject {
        parametersOf(
            { show: Show ->
                startActivity(
                    Intent(this, SerieDetailActivity::class.java)
                        .putExtra("tvShowId", show.id)
                        .putExtra("tvShowName", show.name)
                )
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_series)

        supportActionBar?.title = getString(R.string.series_activity)

        lError_btRefresh.setOnClickListener { viewModel.getSeries() }
    }

    private fun setupObservable() {
        viewModel.getSeries()

        viewModel.run {
            observe(seriesLiveData) {
                it?.run {
                    listAdapter.submitList(this)
                }
            }
            observe(contendVisibility) {
                it?.run {
                    main.visibility = this
                }
            }
            observe(errorVisibility) {
                it?.run {
                    layoutError.visibility = this
                }
            }
            observe(mainLoaderVisibility) {
                it?.run {
                    loadingLayout.visibility = this
                }
            }

        }
    }

    private fun setupRecycler() {
        main.apply {
            this.adapter = listAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            itemAnimator = DefaultItemAnimator()
        }
    }

    override fun onStart() {
        super.onStart()
        setupRecycler()
        setupObservable()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}