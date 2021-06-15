package com.android.moviekade.business.data.repository

import com.android.moviekade.business.domain.entity.Slider
import com.android.moviekade.business.domain.mapper.cache.SliderCacheMapper
import com.android.moviekade.business.domain.mapper.response.SliderResponseMapper
import com.android.moviekade.presentation.database.DataState
import com.android.moviekade.service.datasource.dao.SliderDAO
import com.android.moviekade.service.datasource.network.Api
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class SliderRepo @Inject constructor(
    private val sliderDAO: SliderDAO,
    private val sliderNetwork: Api,
    private val sliderCacheMapper: SliderCacheMapper,
    private val sliderResponseMapper: SliderResponseMapper
) {

    suspend fun getAllItems(): Flow<DataState<List<Slider>>> = flow {
        emit(DataState.Loading)
        try {
            val networkSliderResponse = sliderNetwork.getSlider()
            val sliders = sliderResponseMapper.mapFromList(networkSliderResponse)
            for (slider in sliders) {
                sliderDAO.insertSliderObject(sliderCacheMapper.mapTo(slider))
            }
            val cachedSlider = sliderDAO.getAllSlider()
            emit(DataState.Success(sliderCacheMapper.mapFromList(cachedSlider)))
        }catch (e: Exception) {
            emit(DataState.Error(e.message))
        }
    }
}