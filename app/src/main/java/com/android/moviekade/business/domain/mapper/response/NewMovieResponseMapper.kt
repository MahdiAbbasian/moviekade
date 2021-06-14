package com.android.moviekade.business.domain.mapper.response

import com.android.moviekade.business.data.remote.response.InformationHomeItemNewMovieResponse
import com.android.moviekade.business.domain.entity.NewMovie
import com.android.moviekade.business.domain.mapper.Mapper
import javax.inject.Inject

class NewMovieResponseMapper @Inject constructor():
    Mapper<InformationHomeItemNewMovieResponse, NewMovie> {
    override fun mapFrom(value: InformationHomeItemNewMovieResponse): NewMovie = with(value) {
        NewMovie().also {
            it.id = id!!.toLong()
            it.countryProduct = countryProduct
            it.rateImdb = rateImdb
            it.categoryName = categoryName
            it.director = director
            it.actorsName = actorsName
            it.persianName = persianName
            it.published = published
            it.linkImg = linkImg
            it.name = name
            it.rank = rank
            it.time = time
            it.genreName = genreName
        }
    }

}