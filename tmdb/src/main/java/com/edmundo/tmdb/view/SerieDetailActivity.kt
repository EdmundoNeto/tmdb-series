package com.edmundo.tmdb.view

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.edmundo.domain.model.TvShowDetail
import com.edmundo.tmdb.R
import com.edmundo.tmdb.adapter.GenresAdapter
import com.edmundo.tmdb.utils.observe
import com.edmundo.tmdb.utils.setImage
import com.edmundo.tmdb.utils.toDate
import com.edmundo.tmdb.utils.toDateString
import com.edmundo.tmdb.viewmodel.SerieDetailViewModel
import kotlinx.android.synthetic.main.activity_serie_detail.*
import kotlinx.android.synthetic.main.layout_error.*
import kotlinx.android.synthetic.main.serie_detail_content.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class SerieDetailActivity: AppCompatActivity() {

    private val viewModel: SerieDetailViewModel by viewModel()
    private val listAdapter: GenresAdapter by inject()

    private val tvShowId by lazy { intent.getIntExtra("tvShowId", 0) }
    private val tvShowName by lazy { intent.getStringExtra("tvShowName").orEmpty() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_serie_detail)

        setupToolbar()

        lError_btRefresh.setOnClickListener { viewModel.getTvShow(tvShowId) }
    }

    private fun setupToolbar() {
        supportActionBar?.hide()

        collapsingToolbarLayout.title = tvShowName
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE)

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupObservable() {
        viewModel.getTvShow(tvShowId)

        viewModel.run {
            observe(tvShowDetail) {
                it?.run {
                   setupUi(this)
                }
            }
            observe(contendVisibility) {
                it?.run {
                    contentSerie.visibility = this
                }
            }
            observe(errorVisibility) {
                it?.run {
                    detailLayoutError.visibility = this
                }
            }
            observe(mainLoaderVisibility) {
                it?.run {
                    loaderDetail.visibility = this
                }
            }
            observe(noTvShowFoundVisibility) {
                it?.run {
                    notFound.visibility = this
                }
            }

        }
    }

    private fun setupUi(tvShowDetail: TvShowDetail) {
        if(tvShowDetail.genres.isNotEmpty())
            listAdapter.setGenres(tvShowDetail.genres)

        ivSerieImage.setImage(tvShowDetail.backdrop_path)
        tvSerieDescription.text = tvShowDetail.overview
        tvReleaseDate.text = getString(R.string.first_appearence,
            tvShowDetail.first_air_date.toDate()?.toDateString().orEmpty())
        tvSerieDetailVoteRate.text = getString(R.string.vote_rate,
            tvShowDetail.vote_average.toString())
    }

    private fun setupRecycler() {
        detailGenres.apply {
            this.adapter = listAdapter
            layoutManager = LinearLayoutManager(this@SerieDetailActivity)
        }
    }

    override fun onStart() {
        super.onStart()
        setupRecycler()
        setupObservable()
    }

}