package com.android.moviekade.business.usecase

import com.android.moviekade.business.data.repository.SeriesRepo
import com.android.moviekade.business.domain.entity.Series
import com.android.moviekade.presentation.database.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SeriesUseCase @Inject constructor(private val seriesRepo: SeriesRepo) {
    suspend fun invokeSeries(): Flow<DataState<List<Series>>> {
        return seriesRepo.getAllItems()
    }
}