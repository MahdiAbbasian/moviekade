package com.android.moviekade.business.domain.mapper.cache

import com.android.moviekade.business.domain.entity.AnimationMovie
import com.android.moviekade.business.data.cache.LocalAnimationMovie
import com.android.moviekade.business.domain.mapper.MutualMapper
import javax.inject.Inject

class AnimationMovieCacheMapper @Inject constructor():
    MutualMapper<LocalAnimationMovie, AnimationMovie> {
    override fun mapFrom(value: LocalAnimationMovie): AnimationMovie = with(value) {
        AnimationMovie().also {
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

    override fun mapTo(value: AnimationMovie): LocalAnimationMovie = with(value) {
        LocalAnimationMovie().also {
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
    fun mapFromList(entities: List<LocalAnimationMovie>): List<AnimationMovie> {
        return entities.map { mapFrom(it) }
    }
}