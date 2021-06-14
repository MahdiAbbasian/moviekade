package com.android.moviekade.business.data.remote.response

import com.google.gson.annotations.SerializedName

data class AnimationMovieResponse (
	@field:SerializedName("information_home")
	val informationHomeResponse: List<InformationHomeItemAnimationResponse>? = null
)

data class InformationHomeItemAnimationResponse (

	@field:SerializedName("country_product")
	val countryProduct: String? = null,
	@field:SerializedName("rate_imdb")
	val rateImdb: String? = null,
	@field:SerializedName("category_name")
	val categoryName: String? = null,
	@field:SerializedName("director")
	val director: String? = null,
	@field:SerializedName("actors_name")
	val actorsName: String? = null,
	@field:SerializedName("persian_name")
	val persianName: String? = null,
	@field:SerializedName("published")
	val published: String? = null,
	@field:SerializedName("link_img")
	val linkImg: String? = null,
	@field:SerializedName("name")
	val name: String? = null,
	@field:SerializedName("rank")
	val rank: String? = null,
	@field:SerializedName("id")
	val id: String? = null,
	@field:SerializedName("time")
	val time: String? = null,
	@field:SerializedName("genre_name")
	val genreName: String? = null
)
