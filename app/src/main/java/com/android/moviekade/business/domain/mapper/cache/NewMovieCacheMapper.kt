package com.android.moviekade.business.domain.mapper.cache

import com.android.moviekade.business.domain.entity.NewMovie
import com.android.moviekade.business.data.cache.LocalNewMovie
import com.android.moviekade.business.domain.mapper.MutualMapper
import javax.inject.Inject

class NewMovieCacheMapper @Inject constructor():
    MutualMapper<LocalNewMovie, NewMovie> {
    override fun mapFrom(value: LocalNewMovie): NewMovie = with(value) {
        NewMovie().also {
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

    override fun mapTo(value: NewMovie): LocalNewMovie = with(value) {
        LocalNewMovie().also {
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
    fun mapFromList(entities: List<LocalNewMovie>): List<NewMovie> {
        return entities.map { mapFrom(it) }
    }
}