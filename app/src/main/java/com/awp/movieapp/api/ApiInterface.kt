package com.awp.movieapp.api

import com.awp.movieapp.data.MovieDetailResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("movie/{movie_id}")
    fun getDetailMovies(
        @Path("movie_id")
        id: Int
    ) : Single<MovieDetailResponse>

}