package com.android.moviekade.business.data.repository

import com.android.moviekade.business.domain.entity.TopMovie
import com.android.moviekade.business.domain.mapper.cache.TopMovieCacheMapper
import com.android.moviekade.business.domain.mapper.response.TopMovieResponseMapper
import com.android.moviekade.presentation.database.DataState
import com.android.moviekade.service.datasource.dao.TopMovieDAO
import com.android.moviekade.service.datasource.network.Api
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class TopMovieRepo @Inject constructor(
    private val topMovieDAO: TopMovieDAO,
    private val topMovieNetwork: Api,
    private val topMovieCacheMapper: TopMovieCacheMapper,
    private val topMovieResponseMapper: TopMovieResponseMapper
) {

    suspend fun getAllItems(): Flow<DataState<List<TopMovie>>> = flow {
        emit(DataState.Loading)
        try {
            val networkTopMovieResponse = topMovieNetwork.getTopMovie("top_movie")
            val topMovies = topMovieResponseMapper.mapFromList(networkTopMovieResponse)
            for (topMovie in topMovies) {
                topMovieDAO.insertTopMovieObject(topMovieCacheMapper.mapTo(topMovie))
            }
            val cachedTopMovie = topMovieDAO.getAllTopMovie()
            emit(DataState.Success(topMovieCacheMapper.mapFromList(cachedTopMovie)))
        }catch (e: Exception) {
            emit(DataState.Error(e.message))
        }
    }
}