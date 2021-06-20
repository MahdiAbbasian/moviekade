package com.android.moviekade.business.domain.mapper.response

import com.android.moviekade.business.data.remote.response.SeriesItemResponse
import com.android.moviekade.business.domain.entity.Series
import com.android.moviekade.business.domain.mapper.Mapper
import javax.inject.Inject

class SeriesResponseMapper @Inject constructor():
    Mapper<SeriesItemResponse, Series> {
    override fun mapFrom(value: SeriesItemResponse): Series = with(value) {
        Series().also {
            it.idSeries = idSeries!!.toLong()
            it.countryProduct = countryProduct
            it.rateImdb = rateImdb
            it.categoryName = categoryName
            it.director = director
            it.actorsName = actorsName
            it.persianName = persianName
            it.published = published
            it.countSeasons = countSeasons
            it.linkImg = linkImg
            it.name = name
            it.time = time
            it.genreName = genreName
        }
    }
    fun mapFromList(entities: List<SeriesItemResponse>): List<Series> {
        return entities.map { mapFrom(it) }
    }
}