package com.awp.movieapp.repository

import android.net.Network
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.awp.movieapp.api.ApiInterface
import com.awp.movieapp.data.MovieDetailResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NetworkDataSource(private val apiInterface: ApiInterface, private val compositeDisposable: CompositeDisposable) {

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
    get() = _networkState

    private val _downloadMovieDetailResponse = MutableLiveData<MovieDetailResponse>()
    val downloadMovieDetailResponse: LiveData<MovieDetailResponse>
    get() = _downloadMovieDetailResponse

    fun fetchMovieDetail(movieId: Int) {

        _networkState.postValue(NetworkState.LOADING)

        try {
            compositeDisposable.add(
                apiInterface.getDetailMovies(movieId)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _downloadMovieDetailResponse.postValue(it)
                            _networkState.postValue(NetworkState.LOADED)
                        },
                        {
                            _networkState.postValue(NetworkState.ERROR)
                            Log.e("NetworkDataSource", it.message.toString())
                        }
                    )
            )
        } catch (e:Exception) {
            Log.e("NetworkDataSource", e.message.toString())
        }

    }

}