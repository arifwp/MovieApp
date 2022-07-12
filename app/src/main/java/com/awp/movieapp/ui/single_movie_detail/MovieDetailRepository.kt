package com.awp.movieapp.ui.single_movie_detail

import androidx.lifecycle.LiveData
import com.awp.movieapp.api.ApiInterface
import com.awp.movieapp.data.MovieDetailResponse
import com.awp.movieapp.repository.NetworkDataSource
import com.awp.movieapp.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable

class MovieDetailRepository(private val apiInterface: ApiInterface) {

    lateinit var networkDataSource: NetworkDataSource

    fun fetchSingleMovieDetail(compositeDisposable: CompositeDisposable, movieId: Int) : LiveData<MovieDetailResponse> {
        networkDataSource = NetworkDataSource(apiInterface, compositeDisposable)
        networkDataSource.fetchMovieDetail(movieId)

        return networkDataSource.downloadMovieDetailResponse
    }

    fun getMovieDetailNetworkState(): LiveData<NetworkState> {
        return networkDataSource.networkState
    }

}