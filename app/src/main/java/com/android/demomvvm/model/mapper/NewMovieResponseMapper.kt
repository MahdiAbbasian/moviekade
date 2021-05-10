package com.android.demomvvm.model.mapper

import com.android.demomvvm.model.entity.NewMovie
import com.android.demomvvm.model.local.LocalNewMovie

class NewMovieResponseMapper: NewMovieMapper<LocalNewMovie, NewMovie> {
    override fun mapTo(value: NewMovie): LocalNewMovie = with(value) {
        return LocalNewMovie().also {
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

    override fun mapFrom(value: LocalNewMovie): NewMovie = with(value) {
        return NewMovie().also {
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