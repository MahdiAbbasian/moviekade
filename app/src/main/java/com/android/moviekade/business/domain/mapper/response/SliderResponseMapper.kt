package com.android.moviekade.business.domain.mapper.response

import com.android.moviekade.business.data.remote.response.SliderItemResponse
import com.android.moviekade.business.domain.entity.Slider
import com.android.moviekade.business.domain.mapper.Mapper
import javax.inject.Inject

class SliderResponseMapper @Inject constructor():
    Mapper<SliderItemResponse, Slider> {
    override fun mapFrom(value: SliderItemResponse): Slider = with(value) {
        Slider().also {
            it.id = id!!.toLong()
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
    fun mapFromList(entities: List<SliderItemResponse>): List<Slider> {
        return entities.map { mapFrom(it) }
    }
}