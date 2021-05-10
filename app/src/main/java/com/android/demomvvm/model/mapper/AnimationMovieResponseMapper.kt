package com.android.demomvvm.model.mapper

import com.android.demomvvm.model.entity.AnimationMovie
import com.android.demomvvm.model.local.LocalAnimationMovie

class AnimationMovieResponseMapper: Mapper<LocalAnimationMovie, AnimationMovie> {
    override fun mapTo(value: AnimationMovie): LocalAnimationMovie = with(value) {
        return LocalAnimationMovie().also {
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

    override fun mapFrom(value: LocalAnimationMovie): AnimationMovie = with(value) {
        return AnimationMovie().also {
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