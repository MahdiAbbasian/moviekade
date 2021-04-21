package com.android.demomvvm.model.data

import com.google.gson.annotations.SerializedName

data class Slider(
    @field:SerializedName("Slider")
    var slider:ArrayList<SliderItem>? = null
)

data class SliderItem(
    @field:SerializedName("country_product")
    var countryProduct: String? = null,
    @field:SerializedName("rate_imdb")
    var rateImdb: String? = null,
    @field:SerializedName("category_name")
    var categoryName: String? = null,
    @field:SerializedName("director")
    var director: String? = null,
    @field:SerializedName("actors_name")
    var actorsName: String? = null,
    @field:SerializedName("persian_name")
    var persianName: String? = null,
    @field:SerializedName("published")
    var published: String? = null,
    @field:SerializedName("link_img")
    var linkImg: String? = null,
    @field:SerializedName("id_slider")
    var idSlider: String? = null,
    @field:SerializedName("id_movie")
    var idMovie: String? = null,
    @field:SerializedName("name")
    var name: String? = null,
    @field:SerializedName("rank")
    var rank: String? = null,
    @field:SerializedName("time")
    var time: String? = null,
    @field:SerializedName("id")
    var id: String? = null,
    @field:SerializedName("genre_name")
    var genreName: String? = null
)
