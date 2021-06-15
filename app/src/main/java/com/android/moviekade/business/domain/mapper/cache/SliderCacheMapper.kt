package com.android.moviekade.business.domain.mapper.cache

import com.android.moviekade.business.domain.entity.Slider
import com.android.moviekade.business.data.cache.LocalSlider
import com.android.moviekade.business.domain.mapper.MutualMapper
import javax.inject.Inject

class SliderCacheMapper @Inject constructor():
    MutualMapper<LocalSlider, Slider> {
    override fun mapFrom(value: LocalSlider): Slider = with(value) {
        Slider().also {
            it.id = id
            it.countryProduct = countryProduct
            it.rateImdb = rateImdb
            it.categoryName = categoryName
            it.director = director
            it.actorsName = actorsName
            it.persianName = persianName
            it.published = published
            it.linkImg = linkImg
            it.idSlider = idSlider
            it.idMovie = idMovie
            it.name = name
            it.rank = rank
            it.time = time
            it.genreName = genreName
        }
    }

    override fun mapTo(value: Slider): LocalSlider = with(value) {
        LocalSlider().also {
            it.countryProduct = countryProduct
            it.rateImdb = rateImdb
            it.categoryName = categoryName
            it.director = director
            it.actorsName = actorsName
            it.persianName = persianName
            it.published = published
            it.linkImg = linkImg
            it.idSlider = idSlider
            it.idMovie = idMovie
            it.name = name
            it.rank = rank
            it.time = time
            it.genreName = genreName
        }
    }
    fun mapFromList(entities: List<LocalSlider>): List<Slider> {
        return entities.map { mapFrom(it) }
    }
}