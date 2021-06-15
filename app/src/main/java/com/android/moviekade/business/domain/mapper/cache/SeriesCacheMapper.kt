package com.android.moviekade.business.domain.mapper.cache

import com.android.moviekade.business.domain.entity.Series
import com.android.moviekade.business.data.cache.LocalSeries
import com.android.moviekade.business.domain.mapper.MutualMapper
import javax.inject.Inject

class SeriesCacheMapper @Inject constructor():
    MutualMapper<LocalSeries, Series> {
    override fun mapFrom(value: LocalSeries): Series = with(value) {
        Series().also {
            it.idSeries = idSeries
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

    override fun mapTo(value: Series): LocalSeries = with(value) {
        LocalSeries().also {
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
    fun mapFromList(entities: List<LocalSeries>): List<Series> {
        return entities.map { mapFrom(it) }
    }
}