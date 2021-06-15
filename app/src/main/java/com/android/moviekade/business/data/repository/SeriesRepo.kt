package com.android.moviekade.business.data.repository

import com.android.moviekade.business.domain.entity.Series
import com.android.moviekade.business.domain.mapper.cache.SeriesCacheMapper
import com.android.moviekade.business.domain.mapper.response.SeriesResponseMapper
import com.android.moviekade.presentation.database.DataState
import com.android.moviekade.service.datasource.dao.SeriesDAO
import com.android.moviekade.service.datasource.network.Api
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class SeriesRepo @Inject constructor(
    private val seriesDAO: SeriesDAO,
    private val seriesNetwork: Api,
    private val seriesCacheMapper: SeriesCacheMapper,
    private val seriesResponseMapper: SeriesResponseMapper
) {

    suspend fun getAllItems(): Flow<DataState<List<Series>>> = flow {
        emit(DataState.Loading)
        try {
            val networkSeriesResponse = seriesNetwork.getSeries("series")
            val allSeries = seriesResponseMapper.mapFromList(networkSeriesResponse)
            for (series in allSeries) {
                seriesDAO.insertSeriesObject(seriesCacheMapper.mapTo(series))
            }
            val cachedSeries = seriesDAO.getAllSeries()
            emit(DataState.Success(seriesCacheMapper.mapFromList(cachedSeries)))

        }catch (e: Exception) {
            emit(DataState.Error(e.message))
        }
    }

}