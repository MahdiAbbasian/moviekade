package com.android.demomvvm.model.mapper

import com.android.demomvvm.model.entity.Series
import com.android.demomvvm.model.local.LocalSeries

class SeriesResponseMapper: SeriesMapper<LocalSeries, Series> {
    override fun mapTo(value: Series): LocalSeries = with(value) {
        return LocalSeries().also {
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

    override fun mapFrom(value: LocalSeries): Series = with(value) {
        return Series().also {
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

}