package com.android.moviekade.business.usecase

import com.android.moviekade.business.data.repository.SliderRepo
import com.android.moviekade.business.domain.entity.Slider
import com.android.moviekade.presentation.database.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SliderUseCase @Inject constructor(private val sliderRepo: SliderRepo) {
    suspend fun invokeSlider(): Flow<DataState<List<Slider>>> {
        return sliderRepo.getAllItems()
    }
}