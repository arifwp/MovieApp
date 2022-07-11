package com.awp.movieapp

import com.google.gson.annotations.SerializedName

data class MovieResponse(

	@field:SerializedName("MovieResponse")
	val movieResponse: List<MovieResponseItem?>? = null
)

data class MovieResponseItem(

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("desc")
	val desc: String? = null
)
