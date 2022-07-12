package com.awp.movieapp.ui.popular_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.awp.movieapp.api.ApiInterface
import com.awp.movieapp.api.POST_PER_PAGE
import com.awp.movieapp.data.Movie
import com.awp.movieapp.repository.MovieDataSource
import com.awp.movieapp.repository.MovieDataSourceFactory
import com.awp.movieapp.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable

class MoviePageListRepository(private val apiInterface: ApiInterface) {

    lateinit var moviePagedList: LiveData<PagedList<Movie>>
    lateinit var moviesDataSourceFactory: MovieDataSourceFactory

    fun fetchLiveMoviePagedList(compositeDisposable: CompositeDisposable) : LiveData<PagedList<Movie>> {
        moviesDataSourceFactory = MovieDataSourceFactory(apiInterface, compositeDisposable)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()

        moviePagedList = LivePagedListBuilder(moviesDataSourceFactory, config).build()

        return moviePagedList
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap<MovieDataSource, NetworkState>(
            moviesDataSourceFactory.movieLiveDataSource, MovieDataSource::networkState
        )
    }

}