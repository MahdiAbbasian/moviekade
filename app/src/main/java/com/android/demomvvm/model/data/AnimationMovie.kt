package com.android.demomvvm.model.data

import com.google.gson.annotations.SerializedName

data class AnimationMovie(

	@field:SerializedName("information_home")
	val informationHome: List<InformationHomeItemAnimation>? = null
)

data class InformationHomeItemAnimation(

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
