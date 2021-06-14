package com.android.moviekade.service.datasource.network

import com.android.moviekade.business.data.remote.response.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET(value = "MovieKade/getIndexSlider.php")
    suspend fun getSlider(): List<SliderResponse>

    @GET(value = "MovieKade/getInformationHome.php?")
    suspend fun getAnimationMovie(@Query("category_name") category_name: String?): List<InformationHomeItemAnimationResponse>

    @GET(value = "MovieKade/getInformationHome.php?")
    suspend fun getTopMovie(@Query("category_name") category_name: String?): List<TopMovieResponse>

    @GET(value = "MovieKade/getInformationHome.php?")
    suspend fun getNewMovie(@Query("category_name") category_name: String?): List<InformationHomeItemNewMovieResponse>

    @GET(value = "MovieKade/getSeries.php?")
    suspend fun getSeries(@Query("category_name") category_name: String?): List<SeriesResponse>
}