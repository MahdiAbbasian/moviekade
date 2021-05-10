package com.android.demomvvm.model.mapper

import com.android.demomvvm.model.entity.TopMovie
import com.android.demomvvm.model.local.LocalTopMovie

class TopMovieResponseMapper: TopMovieMapper<LocalTopMovie, TopMovie> {
    override fun mapTo(value: TopMovie): LocalTopMovie = with(value) {
        return LocalTopMovie().also {
            it.id = id
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

    override fun mapFrom(value: LocalTopMovie): TopMovie = with(value) {
        return TopMovie().also {
            it.id = id
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