package com.android.moviekade.business.domain.mapper.response

import com.android.moviekade.business.data.remote.response.InformationHomeItemTopMovieResponse
import com.android.moviekade.business.domain.entity.TopMovie
import com.android.moviekade.business.domain.mapper.Mapper

class TopMovieResponseMapper: Mapper<InformationHomeItemTopMovieResponse, TopMovie> {
    override fun mapFrom(value: InformationHomeItemTopMovieResponse): TopMovie = with(value) {
        TopMovie().also {
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