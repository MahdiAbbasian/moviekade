package com.android.moviekade.business.domain.mapper.cache

import com.android.moviekade.business.domain.entity.TopMovie
import com.android.moviekade.business.data.cache.LocalTopMovie
import com.android.moviekade.business.domain.mapper.MutualMapper
import javax.inject.Inject

class TopMovieCacheMapper @Inject constructor():
    MutualMapper<LocalTopMovie, TopMovie> {
    override fun mapFrom(value: LocalTopMovie): TopMovie = with(value){
        TopMovie().also {
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

    override fun mapTo(value: TopMovie): LocalTopMovie = with(value) {
        LocalTopMovie().also {
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
    fun mapFromList(entities: List<LocalTopMovie>): List<TopMovie> {
        return entities.map { mapFrom(it) }
    }
}