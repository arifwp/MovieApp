package com.awp.movieapp.repository

import androidx.lifecycle.MutableLiveData
import com.awp.movieapp.api.ApiInterface
import com.awp.movieapp.data.Movie
import io.reactivex.disposables.CompositeDisposable
import androidx.paging.DataSource

class MovieDataSourceFactory(private val apiInterface: ApiInterface, private val compositeDisposable: CompositeDisposable) : DataSource.Factory<Int, Movie>() {

    val movieLiveDataSource = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val movieDataSource = MovieDataSource(apiInterface, compositeDisposable)
        
        movieLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }
}