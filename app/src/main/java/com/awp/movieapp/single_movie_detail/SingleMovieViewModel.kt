package com.awp.movieapp.single_movie_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.awp.movieapp.data.MovieDetailResponse
import com.awp.movieapp.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable

class SingleMovieViewModel(private val movieDetailRepository: MovieDetailRepository, movieId: Int) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val movieDetailResponse : LiveData<MovieDetailResponse> by lazy {
        movieDetailRepository.fetchSingleMovieDetail(compositeDisposable, movieId)
    }

    val networkState : LiveData<NetworkState> by lazy {
        movieDetailRepository.getMovieDetailNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}