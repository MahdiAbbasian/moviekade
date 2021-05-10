package com.android.demomvvm.network

import com.android.demomvvm.model.data.remote.response.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET(value = "MovieKade/getIndexSlider.php")
    suspend fun getSlider(): Response<SliderResponse>

    @GET(value = "MovieKade/getInformationHome.php?")
    suspend fun getAnimationMovie(@Query("category_name") category_name: String?): Response<AnimationMovieResponse>

    @GET(value = "MovieKade/getInformationHome.php?")
    suspend fun getTopMovie(@Query("category_name") category_name: String?): Response<TopMovieResponse>

    @GET(value = "MovieKade/getInformationHome.php?")
    suspend fun getNewMovie(@Query("category_name") category_name: String?): Response<NewMovieResponse>

    @GET(value = "MovieKade/getSeries.php?")
    suspend fun getSeries(@Query("category_name") category_name: String?): Response<SeriesResponse>
}