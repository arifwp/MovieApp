package com.awp.movieapp.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET

interface ApiInterface {

    @GET("movielist.json")
//    fun getAllMovies() : Call<List<Movie>>

    companion object {
        var apiInterface: ApiInterface? = null

        fun getInstance() : ApiInterface {
            if(apiInterface == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("")
            }
        }
    }

}