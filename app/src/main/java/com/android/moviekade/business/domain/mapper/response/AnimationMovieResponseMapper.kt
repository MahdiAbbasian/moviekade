package com.android.moviekade.business.domain.mapper.response

import com.android.moviekade.business.data.remote.response.InformationHomeItemAnimationResponse
import com.android.moviekade.business.domain.entity.AnimationMovie
import com.android.moviekade.business.domain.mapper.Mapper
import javax.inject.Inject

class AnimationMovieResponseMapper @Inject constructor() :
    Mapper<InformationHomeItemAnimationResponse, AnimationMovie> {
    override fun mapFrom(value: InformationHomeItemAnimationResponse): AnimationMovie = with(value) {
        AnimationMovie().also {
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
    fun mapFromList(entities: List<InformationHomeItemAnimationResponse>): List<AnimationMovie> {
        return entities.map { mapFrom(it) }
    }
}
