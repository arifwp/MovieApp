package com.awp.movieapp.ui.single_movie_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.awp.movieapp.R
import com.awp.movieapp.api.ApiInterface
import com.awp.movieapp.api.Client
import com.awp.movieapp.api.POSTER_BASE_URL
import com.awp.movieapp.data.MovieDetailResponse
import com.awp.movieapp.repository.NetworkState
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_single_movie.*
import java.text.NumberFormat
import java.util.*

class SingleMovie : AppCompatActivity() {

    private lateinit var viewModel: SingleMovieViewModel
    private lateinit var movieDetailRepository: MovieDetailRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_movie)

        val movieId: Int = intent.getIntExtra("id", 1)

        val apiInterface : ApiInterface = Client.getClient()
        movieDetailRepository = MovieDetailRepository(apiInterface)

        viewModel = getViewModel(movieId)

        viewModel.movieDetailResponse.observe(this, Observer {
            bindUI(it)
        })

        viewModel.networkState.observe(this, Observer {
            progress_bar.visibility = if (it == NetworkState.LOADING) {
                View.VISIBLE
            } else {
                View.GONE
            }

            txt_error.visibility = if(it == NetworkState.ERROR) {
                View.VISIBLE
            } else {
                View.GONE
            }
        })
    }

    fun bindUI(it:MovieDetailResponse) {
        movie_title.text = it.title
        movie_tagline.text = it.tagline
        movie_release_date.text = it.releaseDate
        movie_rating.text = it.rating.toString()
        movie_runtime.text = it.runtime.toString() + " minutes"
        movie_overview.text = it.overview

        val formatCurrency = NumberFormat.getCurrencyInstance(Locale.US)
        movie_budget.text = formatCurrency.format(it.budget)
        movie_revenue.text = formatCurrency.format(it.revenue)

        val moviePosterURL = POSTER_BASE_URL + it.posterPath
        Glide.with(this)
            .load(moviePosterURL)
            .into(iv_movie_poster);
    }

    private fun getViewModel(movieId: Int): SingleMovieViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return SingleMovieViewModel(movieDetailRepository,movieId) as T
            }
        })[SingleMovieViewModel::class.java]
    }



}