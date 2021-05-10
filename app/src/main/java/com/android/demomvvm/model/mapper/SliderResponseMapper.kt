package com.android.demomvvm.model.mapper

import com.android.demomvvm.model.entity.Slider
import com.android.demomvvm.model.local.LocalSlider

class SliderResponseMapper: SliderMapper<LocalSlider, Slider> {
    override fun mapTo(value: Slider): LocalSlider = with(value) {
        return LocalSlider().also {
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

    override fun mapFrom(value: LocalSlider): Slider = with(value) {
        return Slider().also {
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

}